package com.swe.lms.book.api.service;

import com.swe.lms.book.api.dto.BookDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface IBookService {
    ResponseEntity<?> searchBooks(String keyword);

    ResponseEntity<?> checkin(List<Integer> bookIds);

    ResponseEntity<?> checkout(List<Integer> bookIds);

    ResponseEntity<?> getCheckoutRecords(Integer userId);
}
