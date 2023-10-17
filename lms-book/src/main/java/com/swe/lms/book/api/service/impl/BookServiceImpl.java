package com.swe.lms.book.api.service.impl;

import com.swe.lms.book.api.adapter.CheckoutRecordAdapter;
import com.swe.lms.book.api.constant.BookStatus;
import com.swe.lms.book.api.constant.LmsConst;
import com.swe.lms.book.api.dto.BookCopyDTO;
import com.swe.lms.book.api.dto.BookCopyIdDTO;
import com.swe.lms.book.api.dto.CheckoutRecordDTO;
import com.swe.lms.book.api.dto.ConfigDTO;
import com.swe.lms.book.api.feign.IAdminFeignClient;
import com.swe.lms.book.api.model.CheckoutRecord;
import com.swe.lms.book.api.repository.CheckoutRepository;
import com.swe.lms.book.api.service.IBookService;
import com.swe.lms.book.api.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service("BookService")
public class BookServiceImpl implements IBookService {
    @Autowired
    private IAdminFeignClient adminFeignClient;

    @Autowired
    private CheckoutRepository checkoutRepository;

    @Override
    public ResponseEntity<?> searchBooks(String keyword) {
        return adminFeignClient.searchBooks(keyword);
    }

    @Override
    public ResponseEntity<?> checkin(List<CheckoutRecordDTO> checkoutRecordDTOs) {
        checkoutRecordDTOs.forEach(record -> {
            record.setCheckinDate(LocalDate.now());
            checkoutRepository.save(CheckoutRecordAdapter.convertToCheckoutRecord(record));

            // Update Book Copy Status
            BookCopyIdDTO copyIdDTO = BookCopyIdDTO.builder().bookId(record.getBookId()).seq(record.getSeq()).build();
            BookCopyDTO bookCopyDTO = BookCopyDTO.builder().copyId(copyIdDTO)
                    .status(BookStatus.AVAILABLE.getValue()).statusDetail("Returned").build();
            adminFeignClient.updateBookCopies(bookCopyDTO);
        });
        return ResponseUtil.createOK("Checkin successfully.");
    }

    @Override
    public ResponseEntity<?> checkout(List<CheckoutRecordDTO> checkoutRecordDTOs) {
        ResponseEntity<List<ConfigDTO>> adminConfigResponse = adminFeignClient.getConfigs();
        if (adminConfigResponse.getStatusCodeValue() != HttpStatus.OK.value()) {
            return ResponseUtil.createInternalServerError("Retrieved configuration failed");
        }
        List<ConfigDTO> configDTOs = adminConfigResponse.getBody();
        int allowableNumOfBooks = Integer.parseInt(configDTOs.stream().filter(cfg -> cfg.getItemName().equals(LmsConst.MAX_BORROWABLE_BOOKS)).collect(Collectors.toList()).get(0).getItemValue());
        int currentBorrowedBook = checkoutRepository.countByUserIdAndCheckinDateIsNull(checkoutRecordDTOs.get(0).getUserId());
        if (checkoutRecordDTOs.size() > allowableNumOfBooks - currentBorrowedBook) {
            return ResponseUtil.createBadRequest(String.format("You can only borrowed %d book(s).", (allowableNumOfBooks - currentBorrowedBook)));
        }
        checkoutRecordDTOs.forEach(record -> {
            record.setCheckoutDate(LocalDate.now());
            CheckoutRecord checkoutRecord = checkoutRepository.save(CheckoutRecordAdapter.convertToCheckoutRecord(record));
            record.setId(checkoutRecord.getId());

            BookCopyIdDTO copyIdDTO = BookCopyIdDTO.builder().bookId(record.getBookId()).seq(record.getSeq()).build();
            BookCopyDTO bookCopyDTO = BookCopyDTO.builder().copyId(copyIdDTO)
                                                           .status(BookStatus.BORROWED.getValue()).statusDetail("Newly borrowed").build();
            adminFeignClient.updateBookCopies(bookCopyDTO);
        });
        return ResponseUtil.createOK(checkoutRecordDTOs, "Checkout successfully.");
    }

    @Override
    public ResponseEntity<?> getCheckoutRecords(Integer userId) {
        List<CheckoutRecord> checkoutRecords = checkoutRepository.findByUserIdAndCheckinDateIsNull(userId);
        if (null != checkoutRecords && checkoutRecords.size() > 0) {
            List<CheckoutRecordDTO> checkoutRecordDTOs = checkoutRecords.stream().map(record -> CheckoutRecordAdapter.convertToCheckoutRecordDTO(record)).collect(Collectors.toList());
            return ResponseUtil.createOK(checkoutRecordDTOs);
        }
        return ResponseUtil.createOK(new ArrayList<>());
    }
}
