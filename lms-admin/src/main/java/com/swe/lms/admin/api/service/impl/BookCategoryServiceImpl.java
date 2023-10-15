package com.swe.lms.admin.api.service.impl;

import com.swe.lms.admin.api.adapter.BookCategoryAdapter;
import com.swe.lms.admin.api.dto.BookCategoryDTO;
import com.swe.lms.admin.api.dto.BookDTO;
import com.swe.lms.admin.api.model.BookCategory;
import com.swe.lms.admin.api.repository.BookCategoryRepository;
import com.swe.lms.admin.api.service.IBookCategoryService;
import com.swe.lms.admin.api.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.Optional;

@Service("BookCategoryService")
public class BookCategoryServiceImpl implements IBookCategoryService {

    @Autowired
    private BookCategoryRepository bookCategoryRepository;
    @Override
    public ResponseEntity<?> insertBookCategory(BookCategoryDTO bookCategoryDTO) {
        BookCategory bookCategory = BookCategoryAdapter.convertToBookCategory(bookCategoryDTO);
        bookCategory = bookCategoryRepository.save(bookCategory);
        bookCategoryDTO.setId(bookCategory.getId());
        return ResponseUtil.createOK(bookCategoryDTO);
    }

    @Override
    public ResponseEntity<?> updateBookCategory(BookCategoryDTO bookCategoryDTO) {
        Optional<BookCategory> optBookCategory = bookCategoryRepository.findById(bookCategoryDTO.getId());
        if (optBookCategory.isPresent()) {
            BookCategory bookCategory = BookCategoryAdapter.convertToBookCategory(bookCategoryDTO);
            bookCategoryRepository.save(bookCategory);
            return ResponseUtil.createOK(bookCategoryDTO);
        }
        return ResponseUtil.createNotFound(String.format("BookCategory ID [%d] is not found.", bookCategoryDTO.getId()));
    }

    @Override
    public ResponseEntity<?> deleteBookCategory(int bookCategoryId) {
        Optional<BookCategory> optBookCategory = bookCategoryRepository.findById(bookCategoryId);
        if (optBookCategory.isPresent()) {
            bookCategoryRepository.deleteById(bookCategoryId);
            return ResponseUtil.createOK(optBookCategory.get(), String.format("BookCategory ID [%d]", bookCategoryId));
        }
        return ResponseUtil.createNotFound(String.format("BookCategory ID [%d] is not found.", bookCategoryId));
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
