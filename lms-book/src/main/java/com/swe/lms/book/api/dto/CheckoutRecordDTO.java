package com.swe.lms.book.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CheckoutRecordDTO {

    private Integer id;

    private Integer bookId;

    private Integer userId;

    private int seq;

    private String title;

    private LocalDate checkinDate;

    private LocalDate checkoutDate;
}
