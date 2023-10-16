package com.swe.lms.book.api.service.impl;

import com.swe.lms.admin.api.adapter.BookAdapter;

import com.swe.lms.book.api.dto.BookDTO;
import com.swe.lms.book.api.model.Book;
import com.swe.lms.book.api.repository.BookRepository;
import com.swe.lms.book.api.service.IBookService;
import com.swe.lms.book.api.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("BookService")
public class BookServiceImpl implements IBookService {
    @Autowired
    private BookRepository bookRepository;
    @Override
    public ResponseEntity<?> searchBooks(Map<String, Object> mapParams) {
        return ResponseUtil.createOK(bookRepository.findAll());
    }

    @Override
    public ResponseEntity<?> searchBooks(String mapParams) {
        return ResponseUtil.createOK(bookRepository.findAll());
    }

    @Override
    public ResponseEntity<?> checkin(List<BookDTO> books) {
        List<Book> booksCheckin = new ArrayList<Book>();
        for(BookDTO book : books){
            Optional<Book> optBook = bookRepository.findById(book.getId());
            if (optBook.isPresent()) {
                Book b= optBook.get();
                b.setAvailable(true);
                booksCheckin.add(b);
                bookRepository.save(b);
            }
        }
        return ResponseUtil.createOK(booksCheckin, "Checkin successfully !");
    }

    @Override
    public ResponseEntity<?> checkout(List<BookDTO> books) {
        List<Book> booksCheckout = new ArrayList<Book>();
        for(BookDTO book : books){
            Optional<Book> optBook = bookRepository.findById(book.getId());
            if (optBook.isPresent() && optBook.get().isAvailable()) {
                Book b= optBook.get();
                b.setAvailable(false);
                bookRepository.save(b);
                booksCheckout.add(optBook.get());
            }
        }

        if (!booksCheckout.isEmpty()) {
            return ResponseUtil.createOK(booksCheckout, String.format("Books are updated", booksCheckout.parallelStream().map(Book::getId).collect(Collectors.toList())));
        }
        else
            return ResponseUtil.createNotFound(String.format("Books not found or not available to checkout."));
    }
}
