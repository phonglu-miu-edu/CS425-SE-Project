package com.swe.lms.admin.api.service;

import com.swe.lms.admin.api.dto.BookCategoryDTO;
import org.springframework.http.ResponseEntity;

public interface IBookCategoryService {

    ResponseEntity<?> insertBookCategory(BookCategoryDTO bookCategoryDTO);

    ResponseEntity<?> updateBookCategory(BookCategoryDTO bookCategoryDTO);

    ResponseEntity<?> deleteBookCategory(int bookCategoryId);

    ResponseEntity<?> getBookCategory(int bookCategoryId);

    ResponseEntity<?> searchBookCategories(String keyword);
}
