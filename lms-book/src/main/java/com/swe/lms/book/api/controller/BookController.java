package com.swe.lms.book.api.controller;

import com.swe.lms.book.api.constant.LmsConst;
import com.swe.lms.book.api.dto.BookDTO;
import com.swe.lms.book.api.model.Book;
import com.swe.lms.book.api.repository.BookRepository;
import com.swe.lms.book.api.service.IBookService;
import com.swe.lms.book.api.util.StringUtil;
import com.swe.lsm.auth.api.constant.HTTPConst;
import com.swe.lsm.auth.api.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
@RefreshScope
@RequestMapping("/lms/book")
public class BookController {
    @Autowired
    private IBookService bookService;

    @PostMapping("/books/checkin")
    public ResponseEntity<?> checkin(@RequestBody List<BookDTO> books) {
        return bookService.checkin(books);
    }

    @PostMapping("/books/checkout")
    public ResponseEntity<?> checkout(@RequestBody List<BookDTO> books) {
        return bookService.checkout(books);
    }

    @GetMapping("/books/{bookId}")
    public ResponseEntity<?> searchBooks(@RequestParam(name="q") String strQuery) {
        Map<String, Object> mapParams = new HashMap<>(StringUtil.parseQueryParam(strQuery, LmsConst.PARAM_DELIM));
        return bookService.searchBooks(mapParams);
    }
}
