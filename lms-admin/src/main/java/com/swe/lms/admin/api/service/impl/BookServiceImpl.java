package com.swe.lms.admin.api.service.impl;

import com.swe.lms.admin.api.constant.HTTPConst;
import com.swe.lms.admin.api.constant.LmsConst;
import com.swe.lms.admin.api.dto.BookDTO;
import com.swe.lms.admin.api.dto.UserDTO;
import com.swe.lms.admin.api.model.Book;
import com.swe.lms.admin.api.repository.BookRepository;
import com.swe.lms.admin.api.service.IBookService;
import com.swe.lms.admin.api.util.ResponseUtil;
import com.swe.lms.admin.api.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("BookService")
public class BookServiceImpl implements IBookService {
    @Autowired
    private BookRepository bookRepository;
    @Override
    public ResponseEntity<?> insertBook(BookDTO bookDTO) {
        bookRepository.save(new Book(bookDTO.title, bookDTO.isbn));
        return ResponseUtil.createOK(bookDTO);
    }

    @Override
    public ResponseEntity<?> updateBook(BookDTO bookDTO) {
        bookRepository.save(new Book(bookDTO.title, bookDTO.isbn));
        return ResponseUtil.createOK(bookDTO);
    }

    @Override
    public ResponseEntity<?> deleteBook(long bookId) {
        bookRepository.deleteById(bookId);
        return ResponseUtil.deleteOK(HTTPConst.MESSAGE);
    }

    @Override
    public ResponseEntity<?> getBook(int bookId) {
        Optional<Book> result = bookRepository.findById(bookId);
        if (result.isPresent()) {
            return ResponseUtil.createOK(result, HTTPConst.STATUS_CODE);
        } else {
            return ResponseUtil.createNotFound(HTTPConst.STATUS_CODE);
        }
    }

    @Override
    public ResponseEntity<?> searchBooks(Map<String, Object> mapParams) {
        String title = (String) mapParams.get("title");

        List<Book> books = bookRepository.findByTitleContaining(title);
        return (ResponseEntity<?>) books;
    }
}
