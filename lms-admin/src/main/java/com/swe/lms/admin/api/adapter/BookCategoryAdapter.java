package com.swe.lms.admin.api.adapter;

import com.swe.lms.admin.api.dto.BookCategoryDTO;
import com.swe.lms.admin.api.model.BookCategory;

public class BookCategoryAdapter {

    public static BookCategoryDTO convertToBookCategoryDTO(BookCategory bookCategory) {
        return BookCategoryDTO.builder()
                .id(bookCategory.getId())
                .categoryName(bookCategory.getCategoryName())
                .description(bookCategory.getDescription())
                .build();
    }

    public static BookCategory convertToBookCategory(BookCategoryDTO bookCategoryDTO) {
        return BookCategory.builder()
                .id(bookCategoryDTO.getId())
                .categoryName(bookCategoryDTO.getCategoryName())
                .description(bookCategoryDTO.getDescription())
                .build();
    }
}
