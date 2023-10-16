package com.swe.lms.book.api.service.impl;

import com.swe.lms.book.api.dto.BookDTO;
import com.swe.lms.book.api.feign.IAdminFeignClient;
import com.swe.lms.book.api.service.IBookService;
import com.swe.lms.book.api.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("BookService")
public class BookServiceImpl implements IBookService {
    @Autowired
    private IAdminFeignClient adminFeignClient;

    @Override
    public ResponseEntity<?> searchBooks(String keyword) {
        return adminFeignClient.searchBooks(keyword);
    }

    @Override
    public ResponseEntity<?> checkin(List<Integer> bookIds) {
        return ResponseUtil.createOK(null, "Checkin successfully !");
    }

    @Override
    public ResponseEntity<?> checkout(List<Integer> bookIds) {
        return ResponseUtil.createOK(null, "Checkout successfully !");
    }

    @Override
    public ResponseEntity<?> getCheckoutRecords(Integer userId) {
        return ResponseUtil.createOK(null, "Checkout successfully !");
    }
}
