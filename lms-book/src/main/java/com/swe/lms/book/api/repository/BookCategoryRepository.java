package com.swe.lms.book.api.repository;

import com.swe.lms.book.api.model.BookCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookCategoryRepository extends JpaRepository<BookCategory, Long> {
    List<BookCategory> findByCategoryNameContaining(String categoryName);
    Optional<BookCategory> findById(Integer id);
}
