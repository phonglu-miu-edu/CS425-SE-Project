package com.swe.lms.book.api.service;

import com.swe.lms.book.api.dto.CheckoutRecordDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IBookService {
    ResponseEntity<?> searchBooks(String keyword);

    ResponseEntity<?> checkin(List<CheckoutRecordDTO> checkoutRecordDTOs);

    ResponseEntity<?> checkout(List<CheckoutRecordDTO> checkoutRecordDTOs);

    ResponseEntity<?> getCheckoutRecords(Integer userId);
}
