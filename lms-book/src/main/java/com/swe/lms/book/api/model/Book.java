package com.swe.lms.book.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="Book")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotBlank
    private String title;

    @NotBlank
    private String isbn;

    @NotBlank
    private String authors;

    @NotNull
    private Integer numOfCopies;

    @NotNull
    private Integer bookCategoryId;
    @NotNull
    private boolean available;

}
