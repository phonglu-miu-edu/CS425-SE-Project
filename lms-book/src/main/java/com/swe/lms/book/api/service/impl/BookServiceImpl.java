package com.swe.lms.book.api.service.impl;

import com.swe.lms.book.api.dto.BookDTO;
import com.swe.lms.book.api.service.IBookService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("BookService")
public class BookServiceImpl implements IBookService {
    @Override
    public ResponseEntity<?> searchBooks(String mapParams) {
        return null;
    }

    @Override
    public ResponseEntity<?> checkin(List<BookDTO> books) {
        return null;
    }

    @Override
    public ResponseEntity<?> checkout(List<BookDTO> books) {
        return null;
    }
}
