package com.swe.lms.book.api.service;


import com.swe.lms.book.api.model.Book;
import com.swe.lms.book.api.model.CheckoutRecord;
import com.swe.lms.book.api.repository.BookRepository;
import com.swe.lms.book.api.repository.CheckoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Date;

@Service("InitDataService")
public class InitializeDataService {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CheckoutRepository checkoutRepository;

    @PostConstruct
    public void initializeData() {
        // Initialize Book Data
        Book book1 = Book.builder().title("Effective Java").isbn("1000").authors("Joshua Bloch").numOfCopies(5).bookCategoryId(1).available(true).build();
        Book book2 = Book.builder().title("Core Java").isbn("1001").authors("Cay S.Horstmann").numOfCopies(10).bookCategoryId(1).available(true).build();
        Book book3 = Book.builder().title("Core Java").isbn("1002").authors("Herbert Schildt").numOfCopies(8).bookCategoryId(1).available(true).build();

        Book book4 = Book.builder().title("C# and .NET").isbn("1003").authors("Christian Nagel").numOfCopies(6).bookCategoryId(2).available(false).build();
        Book book5 = Book.builder().title("Pro C# 10 with .NET 6").isbn("1004").authors("Andrew Troelsen, Phil Japikse").numOfCopies(7).bookCategoryId(2).available(false).build();
        Book book6 = Book.builder().title(".NET Component").isbn("1005").authors("Juval Lowy").bookCategoryId(2).numOfCopies(9).available(false).build();

        bookRepository.save(book1);
        bookRepository.save(book2);
        bookRepository.save(book3);

        bookRepository.save(book4);
        bookRepository.save(book5);
        bookRepository.save(book6);

        CheckoutRecord checkoutRecord = CheckoutRecord.builder()
                .bookId(book1.getId())
                .authors(book1.getAuthors())
                .borrowDate(new Date())
                .isbn(book1.getIsbn())
                .title(book1.getTitle())
                .build();
        checkoutRepository.save(checkoutRecord);

    }
}
