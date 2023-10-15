package com.swe.lms.admin.api.repository;

import com.swe.lms.admin.api.model.BookCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookCategoryRepository extends JpaRepository<BookCategory, Integer> {
    List<BookCategory> findByCategoryNameContaining(String categoryName);
    Optional<BookCategory> findById(Integer id);
}
