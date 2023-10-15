package com.swe.lms.admin.api.adapter;

import com.swe.lms.admin.api.dto.BookDTO;
import com.swe.lms.admin.api.model.Book;

public class BookAdapter {
    public static BookDTO convertToBookDTO(Book book) {
        return BookDTO.builder()
                .id(book.getId())
                .title(book.getTitle())
                .authors(book.getAuthors())
                .isbn(book.getIsbn())
                .bookCategoryId(book.getBookCategoryId())
                .build();
    }

    public static Book convertToBook(BookDTO bookDTO) {
        return Book.builder()
                .title(bookDTO.getTitle())
                .authors(bookDTO.getAuthors())
                .isbn(bookDTO.getIsbn())
                .bookCategoryId(bookDTO.getBookCategoryId())
                .build();
    }
}
