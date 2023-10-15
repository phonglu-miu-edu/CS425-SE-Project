package com.swe.lms.book.api.service;

import com.swe.lms.book.api.dto.BookDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface IBookService {
    ResponseEntity<?> searchBooks(Map<String, Object> mapParams);
    ResponseEntity<?> searchBooks(String mapParams);

    ResponseEntity<?> checkin(List<BookDTO> books);

    ResponseEntity<?> checkout(List<BookDTO> books);
}
