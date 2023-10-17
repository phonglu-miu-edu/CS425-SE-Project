package com.swe.lms.book.api.service.impl;

import com.swe.lms.auth.api.constant.DTOConst;
import com.swe.lms.auth.api.constant.HTTPConst;
import com.swe.lms.book.api.dto.BookDTO;
import com.swe.lms.book.api.feign.IAdminFeignClient;
import com.swe.lms.book.api.model.Book;
import com.swe.lms.book.api.model.CheckoutRecord;
import com.swe.lms.book.api.repository.CheckoutRepository;
import com.swe.lms.book.api.service.IBookService;
import com.swe.lms.book.api.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;


@Service("BookService")
public class BookServiceImpl implements IBookService {
    @Autowired
    private IAdminFeignClient adminFeignClient;

    @Autowired
    private CheckoutRepository checkoutRepository;

    @Override
    public ResponseEntity<?> searchBooks(String keyword) {
//        ResponseEntity<?> response = adminFeignClient.searchBooks(keyword);
//        if (response.getStatusCodeValue() != HttpStatus.OK.value()) {
//            return ResponseUtil.createUnauthorize("Book not found.");
//        }
//        String value = response.getBody().toString();
        return ResponseUtil.createOK(null, String.format("Search result"));
    }

    @Override
    public ResponseEntity<?> checkin(List<Integer> bookIds) {
        List<BookDTO> booksCheckin = new ArrayList<BookDTO>();
        for(Integer id : bookIds){
            ResponseEntity<Book> response = (ResponseEntity<Book>) adminFeignClient.getBook(id);
            if (response.getStatusCodeValue() != HttpStatus.OK.value()) {
                return ResponseUtil.createUnauthorize("Book not found.");
            }

//            Map<BookDTO, Object> mapValue = (Map<BookDTO, Object>)response.getBody();
//            String roleCd = mapValue.get(DTOConst.ROLE_CD).toString();

            //remove from checkout records
//            BookDTO book = mapValue.get();
//            List<CheckoutRecord> checkoutRecord = checkoutRepository.findByTitleContaining(book.getTitle());
//            checkoutRepository.deleteAll(checkoutRecord);
        }
        return ResponseUtil.createOK( booksCheckin,"Checkin successfully !");
    }

    @Override
    public ResponseEntity<?> checkout(List<Integer> bookId) {
        List<BookDTO> booksCheckin = new ArrayList<BookDTO>();
        for(Integer id : bookId){
            ResponseEntity<?> response = adminFeignClient.getBook(id);
            if (response.getStatusCodeValue() != HttpStatus.OK.value()) {
                return ResponseUtil.createUnauthorize("Book not found.");
            }
            BookDTO book = (BookDTO) response.getBody();
            CheckoutRecord checkoutRecord = CheckoutRecord.builder()
                            .bookId(book.getId())
                            .authors(book.getAuthors())
                            .borrowDate(new Date())
                            .isbn(book.getIsbn())
                            .title(book.getTitle())
                            .build();
                booksCheckin.add(book);
                checkoutRepository.save(checkoutRecord);
            }
        return ResponseUtil.createOK( booksCheckin,"Checkout successfully !");
    }

    @Override
    public ResponseEntity<?> getCheckoutRecords(Integer userId) {
        Optional<CheckoutRecord> checkoutRecord = checkoutRepository.findById(userId);
        if (checkoutRecord.isPresent()) {
            return ResponseUtil.createOK(checkoutRecord);
        }
        return ResponseUtil.createNotFound(String.format("Book Category ID [%d] is not found", userId));
    }
}
