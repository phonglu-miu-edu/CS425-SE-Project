package com.swe.lms.book.api.controller;

import com.swe.lms.book.api.dto.CheckoutRecordDTO;
import com.swe.lms.book.api.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RefreshScope
@RequestMapping("/lms/book")
public class BookController {
    @Autowired
    private IBookService bookService;

    @PostMapping("/checkin")
    public ResponseEntity<?> checkin(@RequestBody List<CheckoutRecordDTO> checkoutRecordDTOs) {
        return bookService.checkin(checkoutRecordDTOs);
    }

    @PostMapping("/checkout")
    public ResponseEntity<?> checkout(@RequestBody List<CheckoutRecordDTO> checkoutRecordDTOs) {
        return bookService.checkout(checkoutRecordDTOs);
    }

    @GetMapping("/checkout/records/{userId}")
    public ResponseEntity<?> getCheckoutRecords(@PathVariable(name="userId") int userId) {
        return bookService.getCheckoutRecords(userId);
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchBooks(@RequestParam(required = false, name="q") String keyword) {
        return bookService.searchBooks(keyword);
    }
}
