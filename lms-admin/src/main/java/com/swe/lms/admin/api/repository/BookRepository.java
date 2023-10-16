package com.swe.lms.admin.api.repository;

import com.swe.lms.admin.api.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    List<Book> findByTitleLikeIgnoreCaseOrIsbnLikeIgnoreCaseOrAuthorsLikeIgnoreCase(String title, String isbn, String authors);
    List<Book> findByBookCategoryId(Integer bookCategoryId);
}
