package com.swe.lms.admin.api.service;

import com.swe.lms.admin.api.model.Book;
import com.swe.lms.admin.api.model.BookCategory;
import com.swe.lms.admin.api.model.User;
import com.swe.lms.admin.api.repository.BookCategoryRepository;
import com.swe.lms.admin.api.repository.BookRepository;
import com.swe.lms.admin.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service("InitDataService")
public class InitializeDataService {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookCategoryRepository bookCategoryRepository;

    @PostConstruct
    public void initializeData() {

        // Initialize User Data
        User user1 = User.builder().userName("bao").firstName("Bao Quoc").lastName("Nguyen").roleCd("Admin").password("123").build();
        User user2 = User.builder().userName("phong").firstName("Tai Phong").lastName("Lu").roleCd("Admin").password("123").build();
        userRepository.save(user1);
        userRepository.save(user2);

        // Initialize BookCategory Data
        BookCategory bookCategory1 = BookCategory.builder().categoryName("Java").description("Java technology related books").build();
        BookCategory bookCategory2 = BookCategory.builder().categoryName(".NET").description(".NET technology related books").build();
        bookCategoryRepository.save(bookCategory1);
        bookCategoryRepository.save(bookCategory2);

        // Initialize Book Data
        Book book1 = Book.builder().title("Effective Java").isbn("1000").authors("Joshua Bloch").build();
        Book book2 = Book.builder().title("Core Java").isbn("1001").authors("Cay S.Horstmann").build();
        Book book3 = Book.builder().title("Core Java").isbn("1002").authors("Herbert Schildt").build();
        bookRepository.save(book1);
        bookRepository.save(book2);
        bookRepository.save(book3);


    }
}
