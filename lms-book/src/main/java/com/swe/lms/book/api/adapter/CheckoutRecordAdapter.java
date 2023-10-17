package com.swe.lms.book.api.adapter;

import com.swe.lms.book.api.dto.CheckoutRecordDTO;
import com.swe.lms.book.api.model.CheckoutRecord;

public class CheckoutRecordAdapter {
    public static CheckoutRecord convertToCheckoutRecord(CheckoutRecordDTO checkoutRecordDTO) {
        return CheckoutRecord.builder()
                .id(checkoutRecordDTO.getId())
                .userId(checkoutRecordDTO.getUserId())
                .bookId(checkoutRecordDTO.getBookId())
                .seq(checkoutRecordDTO.getSeq())
                .checkinDate(checkoutRecordDTO.getCheckinDate())
                .checkoutDate(checkoutRecordDTO.getCheckoutDate())
                .build();
    }

    public static CheckoutRecordDTO convertToCheckoutRecordDTO(CheckoutRecord checkoutRecord) {
        return CheckoutRecordDTO.builder()
                .id(checkoutRecord.getId())
                .userId(checkoutRecord.getUserId())
                .bookId(checkoutRecord.getBookId())
                .seq(checkoutRecord.getSeq())
                .checkinDate(checkoutRecord.getCheckinDate())
                .checkoutDate(checkoutRecord.getCheckoutDate())
                .build();
    }
}
