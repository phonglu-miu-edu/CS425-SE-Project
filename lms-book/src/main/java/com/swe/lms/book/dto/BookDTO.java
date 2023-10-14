package com.swe.lms.book.dto;

public class BookDTO {
    public Integer id;
    public String title;
    public String isbn;

    public BookDTO(Integer id, String title, String ISBN) {
        this.id = id;
        this.title = title;
        this.isbn = ISBN;
    }

    public BookDTO() {
    }

    @Override
    public String toString() {
        return "BookDTO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", ISBN='" + isbn + '\'' +
                '}';
    }
}
