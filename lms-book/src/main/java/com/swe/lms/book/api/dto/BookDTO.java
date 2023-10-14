package com.swe.lms.book.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookDTO {
    private Integer id;
    private String title;
    private String isbn;
    private String authors;
}
