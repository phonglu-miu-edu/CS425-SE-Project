package com.swe.lms.book.api.service;

import com.swe.lms.book.api.dto.BookDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface IBookService {
    ResponseEntity<?> searchBooks(String keyword);

    ResponseEntity<?> checkin(List<BookDTO> books);

    ResponseEntity<?> checkout(List<BookDTO> books);

    ResponseEntity<?> getCheckoutRecords(Integer userId);
}
