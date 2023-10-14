package com.swe.lms.admin.api.repository;

import com.swe.lms.book.model.BookCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookCategoryRepository extends JpaRepository<BookCategory, Long> {
    List<BookCategory> findByCategoryNameContaining(String categoryName);
    Optional<BookCategory> findById(Integer id);
}
