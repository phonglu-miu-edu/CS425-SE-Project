package com.swe.lms.book.api.controller;


import com.swe.lms.book.api.constant.LmsConst;
import com.swe.lms.book.api.dto.BookDTO;
import com.swe.lms.book.api.service.IBookService;
import com.swe.lms.book.api.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
@RefreshScope
@RequestMapping("/lms/book")
public class BookController {
    @Autowired
    private IBookService bookService;

    @PostMapping("/checkin")
    public ResponseEntity<?> checkin(@RequestBody List<BookDTO> books) {
        return bookService.checkin(books);
    }

    @PostMapping("/checkout")
    public ResponseEntity<?> checkout(@RequestBody List<BookDTO> books) {
        return bookService.checkout(books);
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchBooks(@RequestParam(required = false, name="q") String keyword) {
        return bookService.searchBooks(keyword);
    }
}
