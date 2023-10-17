package com.swe.lms.book.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;


@Entity
@Table(name="CheckoutRecord")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CheckoutRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @NotNull
    private Integer bookId;
    @NotNull
    private Integer userId;
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
