package com.swe.lms.admin.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="BookCategory")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String categoryName;

    private String description;
}
