package com.swe.lms.admin.api.service.impl;

import com.swe.lms.admin.api.adapter.BookAdapter;
import com.swe.lms.admin.api.constant.HTTPConst;
import com.swe.lms.admin.api.dto.BookDTO;
import com.swe.lms.admin.api.model.Book;
import com.swe.lms.admin.api.repository.BookRepository;
import com.swe.lms.admin.api.service.IBookService;
import com.swe.lms.admin.api.util.ResponseUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("BookService")
public class BookServiceImpl implements IBookService {
    @Autowired
    private BookRepository bookRepository;

    @Override
    public ResponseEntity<?> insertBook(BookDTO bookDTO) {
        Book book = BookAdapter.convertToBook(bookDTO);
        book = bookRepository.save(book);
        bookDTO.setId(book.getId());
        return ResponseUtil.createOK(bookDTO);
    }

    @Override
    public ResponseEntity<?> updateBook(BookDTO bookDTO) {
        Optional<Book> optBook = bookRepository.findById(bookDTO.getId());
        if (optBook.isPresent()) {
            Book book = BookAdapter.convertToBook(bookDTO);
            bookRepository.save(book);
            return ResponseUtil.createOK(bookDTO, String.format("Book [%d] is updated", bookDTO.getId()));
        }
        return ResponseUtil.createNotFound(String.format("Book ID [%d] is not found.", bookDTO.getId()));
    }

    @Override
    public ResponseEntity<?> deleteBook(int bookId) {
        Optional<Book> optBook = bookRepository.findById(bookId);
        if (optBook.isPresent()) {
            bookRepository.deleteById(bookId);
            return ResponseUtil.createOK(optBook.get(), String.format("Book [%d] is deleted successfully", bookId));
        }
        return ResponseUtil.createNotFound(String.format("Book ID [%d] is not found.", bookId));
    }

    @Override
    public ResponseEntity<?> getBook(int bookId) {
        Optional<Book> optBook = bookRepository.findById(bookId);
        if (optBook.isPresent()) {
            return ResponseUtil.createOK(optBook.get(), HTTPConst.STATUS_CODE);
        } else {
            return ResponseUtil.createNotFound(String.format("Book ID[%d] is not found."));
        }
    }

    @Override
    public ResponseEntity<?> searchBooks(String keyword) {
        List<Book> books;
        if (StringUtils.isBlank(keyword)) {
            books = bookRepository.findAll();
        } else {
            books = bookRepository.findByTitleLikeIgnoreCaseOrIsbnLikeOrAuthorsLikeIgnoreCase(keyword, keyword, keyword);
        }
        List<BookDTO> bookDTOs = new ArrayList<>();
        if (null != books && books.size() > 0) {
            bookDTOs = books.stream().map(book -> BookAdapter.convertToBookDTO(book)).collect(Collectors.toList());
            return ResponseUtil.createOK(bookDTOs);
        }
        return ResponseUtil.createOK(bookDTOs);
    }
}
