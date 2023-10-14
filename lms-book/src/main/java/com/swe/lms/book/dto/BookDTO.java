package com.swe.lms.book.dto;

public class BookDTO {
    public Integer id;
    public String title;
    public String ISBN;

    public BookDTO(Integer id, String title, String ISBN) {
        this.id = id;
        this.title = title;
        this.ISBN = ISBN;
    }

    public BookDTO() {
    }

    @Override
    public String toString() {
        return "BookDTO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", ISBN='" + ISBN + '\'' +
                '}';
    }
}
