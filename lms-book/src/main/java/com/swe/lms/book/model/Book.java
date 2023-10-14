package com.swe.lms.book.model;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@EntityScan
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer id;
    @NotBlank
    public String title;
    @NotBlank
    public String isbn;

    public Book() {
    }

    public Book(Integer id, String title, String ISBN) {
        this.id = id;
        this.title = title;
        this.isbn = ISBN;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getISBN() {
        return isbn;
    }

    public void setISBN(String ISBN) {
        this.isbn = ISBN;
    }
}
