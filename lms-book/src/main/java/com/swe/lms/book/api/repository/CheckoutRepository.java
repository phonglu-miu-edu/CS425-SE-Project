package com.swe.lms.book.api.repository;

import com.swe.lms.book.api.model.Book;
import com.swe.lms.book.api.model.CheckoutRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CheckoutRepository extends JpaRepository<CheckoutRecord, Long> {
    List<CheckoutRecord> findByTitleContaining(String title);
    Optional<CheckoutRecord> findById(Integer id);
}
