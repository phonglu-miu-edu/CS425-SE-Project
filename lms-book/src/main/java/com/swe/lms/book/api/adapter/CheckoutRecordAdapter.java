package com.swe.lms.book.api.adapter;

import com.swe.lms.book.api.dto.CheckoutRecordDTO;
import com.swe.lms.book.api.model.CheckoutRecord;

public class CheckoutRecordAdapter {
    public static CheckoutRecord convertToCheckoutRecord(CheckoutRecordDTO checkoutRecordDTO) {
        return CheckoutRecord.builder().build();
    }

    public static CheckoutRecordDTO convertToCheckoutRecordDTO(CheckoutRecord checkoutRecord) {
        return CheckoutRecordDTO.builder().build();
    }
}
