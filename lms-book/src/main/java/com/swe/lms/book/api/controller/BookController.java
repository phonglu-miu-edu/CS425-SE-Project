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
    public ResponseEntity<?> checkin(@RequestBody List<Integer> bookIds) {
        return bookService.checkin(bookIds);
    }

    @PostMapping("/checkout")
    public ResponseEntity<?> checkout(@RequestBody List<Integer> bookIds) {
        return bookService.checkout(bookIds);
    }

    @GetMapping("/checkout/records/{userId}")
    public ResponseEntity<?> getCheckoutRecords(@PathVariable(name="userId") int userId) {
        return bookService.getCheckoutRecords(userId);
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchBooks(@RequestParam(name="q") String keyword) {
        return bookService.searchBooks(keyword);
    }
}
