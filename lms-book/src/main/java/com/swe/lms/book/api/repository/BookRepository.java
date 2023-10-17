package com.swe.lms.book.api.repository;

import com.swe.lms.book.api.model.Book;
import com.swe.lms.book.api.model.CheckoutRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


public interface BookRepository extends JpaRepository<Book, Long>{
    List<Book> findByTitleContaining(String title);
    Optional<Book> findById(Integer id);
}
