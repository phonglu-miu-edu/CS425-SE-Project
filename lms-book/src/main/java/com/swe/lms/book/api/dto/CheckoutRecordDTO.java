package com.swe.lms.book.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CheckoutRecordDTO {
    private Integer bookId;

    private Integer userI;

    private String seq;

    private String title;

    private String isbn;

    private String authors;

    private Date borrowDate;
}
