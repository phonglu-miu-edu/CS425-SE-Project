package com.swe.lms.book.repository;

import com.swe.lms.book.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByTitleContaining(String title);
    List<Book> findByTitleContainingIgnoreCase(String title);

    Optional<Book> findById(Integer id);

}
