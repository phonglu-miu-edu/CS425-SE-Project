package com.swe.lms.admin.api.service.impl;

import com.swe.lms.admin.api.dto.BookDTO;
import com.swe.lms.admin.api.dto.UserDTO;
import com.swe.lms.admin.api.service.IBookService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("BookService")
public class BookServiceImpl implements IBookService {
    @Override
    public ResponseEntity<?> insertBook(BookDTO bookDTO) {
        return null;
    }

    @Override
    public ResponseEntity<?> updateBook(BookDTO bookDTO) {
        return null;
    }

    @Override
    public ResponseEntity<?> deleteBook(int bookId) {
        return null;
    }

    @Override
    public ResponseEntity<?> getBook(int bookId) {
        return null;
    }

    @Override
    public ResponseEntity<?> searchBooks(Map<String, Object> mapParams) {
        return null;
    }
}
