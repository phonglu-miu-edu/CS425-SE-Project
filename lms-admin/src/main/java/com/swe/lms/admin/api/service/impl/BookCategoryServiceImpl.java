package com.swe.lms.admin.api.service.impl;

import com.swe.lms.admin.api.adapter.BookCategoryAdapter;
import com.swe.lms.admin.api.dto.BookCategoryDTO;
import com.swe.lms.admin.api.model.Book;
import com.swe.lms.admin.api.model.BookCategory;
import com.swe.lms.admin.api.repository.BookCategoryRepository;
import com.swe.lms.admin.api.repository.BookRepository;
import com.swe.lms.admin.api.service.IBookCategoryService;
import com.swe.lms.admin.api.util.ResponseUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("BookCategoryService")
public class BookCategoryServiceImpl implements IBookCategoryService {

    @Autowired
    private BookCategoryRepository bookCategoryRepository;

    @Autowired
    private BookRepository bookRepository;

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
            List<Book> books = bookRepository.findByBookCategoryId(bookCategoryId);
            if (null != books && books.size() > 0) {
                return ResponseUtil.createBadRequest("Book Category is not empty.");
            }
            bookCategoryRepository.deleteById(bookCategoryId);
            return ResponseUtil.createOK(optBookCategory.get(), String.format("BookCategory ID [%d]", bookCategoryId));
        }
        return ResponseUtil.createNotFound(String.format("BookCategory ID [%d] is not found.", bookCategoryId));
    }

    @Override
    public ResponseEntity<?> getBookCategory(int bookCategoryId) {
        Optional<BookCategory> optBookCategory = bookCategoryRepository.findById(bookCategoryId);
        if (optBookCategory.isPresent()) {
            return ResponseUtil.createOK(BookCategoryAdapter.convertToBookCategoryDTO(optBookCategory.get()));
        }
        return ResponseUtil.createNotFound(String.format("Book Category ID [%d] is not found", bookCategoryId));
    }

    @Override
    public ResponseEntity<?> searchBookCategories(String keyword) {
        List<BookCategory> bookCategories;
        if (StringUtils.isBlank(keyword))
            bookCategories = bookCategoryRepository.findAll();
        else {
            bookCategories = bookCategoryRepository.findByCategoryNameLikeIgnoreCaseOrDescriptionLikeIgnoreCase(keyword, keyword);
        }
        List<BookCategoryDTO> bookCategoryDTOs = new ArrayList<>();
        if (null != bookCategories && bookCategories.size() > 0) {
            bookCategoryDTOs = bookCategories.stream().map(bookCategory -> BookCategoryAdapter.convertToBookCategoryDTO(bookCategory)).collect(Collectors.toList());
            return ResponseUtil.createOK(bookCategoryDTOs);
        }
        return ResponseUtil.createOK(bookCategoryDTOs);
    }
}
