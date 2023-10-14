package com.swe.lms.book.api.repository;

import com.swe.lms.book.api.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByTitleContaining(String title);

    Optional<Book> findById(Integer id);

}
