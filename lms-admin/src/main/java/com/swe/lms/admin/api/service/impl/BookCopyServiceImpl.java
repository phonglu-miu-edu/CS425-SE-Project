package com.swe.lms.admin.api.service.impl;

import com.swe.lms.admin.api.adapter.BookCopyAdapter;
import com.swe.lms.admin.api.constant.BookStatus;
import com.swe.lms.admin.api.dto.BookCopyDTO;
import com.swe.lms.admin.api.dto.BookDTO;
import com.swe.lms.admin.api.model.Book;
import com.swe.lms.admin.api.model.BookCopy;
import com.swe.lms.admin.api.model.BookCopyId;
import com.swe.lms.admin.api.repository.BookCopyRepository;
import com.swe.lms.admin.api.repository.BookRepository;
import com.swe.lms.admin.api.service.IBookCopyService;
import com.swe.lms.admin.api.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("BookCopyService")
public class BookCopyServiceImpl implements IBookCopyService {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookCopyRepository bookCopyRepository;

    @Override
    public ResponseEntity<?> addBookCopies(BookDTO bookDTO) {
        int bookId = bookDTO.getId();
        Optional<Book> optBook = bookRepository.findById(bookDTO.getId());
        if (!optBook.isPresent()) {
            return ResponseUtil.createNotFound(String.format("Book ID [%d] is not found", bookId));
        }
        int numOfCurrentCopies = optBook.get().getNumOfCopies();
        int numOfAdded = bookDTO.getNumOfCopies();
        for (int i=numOfCurrentCopies + 1; i<= numOfCurrentCopies + numOfAdded; i++) {
            BookCopyId bookCopyId = BookCopyId.builder().bookId(bookId).seq(i).build();
            BookCopy bookCopy = BookCopy.builder().copyId(bookCopyId).status(BookStatus.AVAILABLE.getValue()).statusDetail("New").build();
            bookCopyRepository.save(bookCopy);
        }
        return ResponseUtil.createOK(String.format("Added %d copies for Book Id[%d]", numOfAdded, bookId));
    }

    @Override
    public ResponseEntity<?> updateBookCopy(BookCopyDTO bookCopyDTO) {
        Integer bookId = bookCopyDTO.getCopyId().getBookId();
        Optional<Book> optBook = bookRepository.findById(bookCopyDTO.getCopyId().getBookId());
        if (!optBook.isPresent()) {
            return ResponseUtil.createNotFound(String.format("Book ID [%d] is not found", bookId.intValue()));
        }
        BookCopy bookCopy = BookCopyAdapter.convertToBookCopy(bookCopyDTO);
        bookCopyRepository.save(bookCopy);
        return ResponseUtil.createOK("BookCopy information is updated successfully.");
    }

    @Override
    public ResponseEntity<?> getBookCopies(int bookId) {
        Optional<Book> optBook = bookRepository.findById(bookId);
        if (!optBook.isPresent()) {
            return ResponseUtil.createNotFound(String.format("Book ID [%d] is not found", bookId));
        }
        List<BookCopy> bookCopies = bookCopyRepository.findByCopyId_BookId(bookId);
        List<BookCopyDTO> bookCopyDTOs = new ArrayList<>();
        if (null != bookCopies && bookCopies.size() > 0) {
            bookCopyDTOs = bookCopies.stream().map(bookCopy -> BookCopyAdapter.convertToBookCopyDTO(bookCopy)).collect(Collectors.toList());
        }
        return ResponseUtil.createOK(bookCopyDTOs);
    }
}
