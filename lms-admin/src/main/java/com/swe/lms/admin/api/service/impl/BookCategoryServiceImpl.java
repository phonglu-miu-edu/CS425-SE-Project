package com.swe.lms.admin.api.service.impl;

import com.swe.lms.admin.api.dto.BookCategoryDTO;
import com.swe.lms.admin.api.dto.BookDTO;
import com.swe.lms.admin.api.service.IBookCategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("BookCategoryService")
public class BookCategoryServiceImpl implements IBookCategoryService {
    @Override
    public ResponseEntity<?> insertBookCategory(BookCategoryDTO bookCategoryDTO) {
        return null;
    }

    @Override
    public ResponseEntity<?> updateBookCategory(BookCategoryDTO bookCategoryDTO) {
        return null;
    }

    @Override
    public ResponseEntity<?> deleteBookCategory(int bookCategoryId) {
        return null;
    }

    @Override
    public ResponseEntity<?> getBookCategory(int bookCategoryId) {
        return null;
    }

    @Override
    public ResponseEntity<?> searchBookCategories(Map<String, Object> mapParams) {
        return null;
    }
}
