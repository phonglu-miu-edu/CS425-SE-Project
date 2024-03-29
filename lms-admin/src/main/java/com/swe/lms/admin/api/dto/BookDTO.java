package com.swe.lms.admin.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookDTO
{
    private Integer id;

    private String title;

    private String isbn;

    private String authors;

    private Integer numOfCopies;

    private Integer bookCategoryId;

    private boolean available;
}
