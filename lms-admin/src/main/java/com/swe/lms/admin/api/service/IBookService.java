package com.swe.lms.admin.api.service;

import com.swe.lms.admin.api.dto.BookDTO;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface IBookService {

    ResponseEntity<?> insertBook(BookDTO bookDTO);

    ResponseEntity<?> updateBook(BookDTO bookDTO);

    ResponseEntity<?> deleteBook(int bookId);

    ResponseEntity<?> getBook(int bookId);

    ResponseEntity<?> searchBooks(Map<String, Object> mapParams);
}
