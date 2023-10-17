package com.swe.lms.book.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;


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

    @NotNull
    private Integer seq;

    @NotNull
    private LocalDate checkoutDate;

    private LocalDate checkinDate;
}
