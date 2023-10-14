package com.swe.lms.book.model;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@EntityScan
@Entity
public class BookCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    public String categoryName;

    public String description;

    public BookCategory(String categoryName, String description) {
        this.categoryName = categoryName;
        this.description = description;
    }

    public BookCategory() {
    }

    public Integer getId() {
        return id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
