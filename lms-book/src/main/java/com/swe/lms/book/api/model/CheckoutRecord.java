package com.swe.lms.book.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CheckoutRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer bookId;
    @NotNull
    private Integer userI;
    @NotBlank
    private String seq;
    @NotBlank
    private String title;
    @NotBlank
    private String isbn;
    @NotBlank
    private String authors;
    @NotBlank
    private Date borrowDate;
}
