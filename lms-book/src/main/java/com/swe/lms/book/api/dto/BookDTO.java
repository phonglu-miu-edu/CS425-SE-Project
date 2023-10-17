package com.swe.lms.book.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class BookDTO
{
    @Id
    private Integer id;

    private String title;

    private String isbn;

    private String authors;

    private Integer numOfCopies;

    private Integer bookCategoryId;

    private boolean available;

}
