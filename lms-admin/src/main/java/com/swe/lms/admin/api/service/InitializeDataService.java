package com.swe.lms.admin.api.service;

import com.swe.lms.admin.api.constant.UserStatus;
import com.swe.lms.admin.api.model.Book;
import com.swe.lms.admin.api.model.BookCategory;
import com.swe.lms.admin.api.model.Config;
import com.swe.lms.admin.api.model.User;
import com.swe.lms.admin.api.repository.BookCategoryRepository;
import com.swe.lms.admin.api.repository.BookRepository;
import com.swe.lms.admin.api.repository.ConfigRepository;
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

    @Autowired
    private ConfigRepository configRepository;

    @PostConstruct
    public void initializeData() {

        // Initialize User Data
        User user1 = User.builder().userName("bao").firstName("Bao Quoc").lastName("Nguyen").roleCd("Admin")
                .password("123").email("baonguyen@miu.edu").phoneNumber("6418191119")
                .address("123 Chellen Dr, Dallas, TX 73456, US")
                .status(UserStatus.ACTIVE.getValue()).numOfOverdues(0).build();
        User user2 = User.builder().userName("phong").firstName("Tai Phong").lastName("Lu").roleCd("Admin")
                .password("123").email("phonglu@miu.edu").phoneNumber("6418192229")
                .address("124 Chellen Dr, Dallas, TX 73456, US")
                .status(UserStatus.ACTIVE.getValue()).numOfOverdues(0).build();
        User user3 = User.builder().userName("john").firstName("John").lastName("Smith").roleCd("Librarian")
                .password("123").email("baonguyen@miu.edu").phoneNumber("6418193339")
                .address("125 Chellen Dr, Dallas, TX 73456, US").build();
        User user4 = User.builder().userName("andy").firstName("Andy").lastName("Nguyen").roleCd("User")
                .password("123").email("baonguyen@miu.edu").phoneNumber("6418194449")
                .address("126 Chellen Dr, Dallas, TX 73456, US")
                .status(UserStatus.ACTIVE.getValue()).numOfOverdues(0).build();

        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
        userRepository.save(user4);

        // Initialize BookCategory Data
        BookCategory bookCategory1 = BookCategory.builder().categoryName("Java").description("Java technology related books").build();
        BookCategory bookCategory2 = BookCategory.builder().categoryName(".NET").description(".NET technology related books").build();
        bookCategoryRepository.save(bookCategory1);
        bookCategoryRepository.save(bookCategory2);

        // Initialize Book Data
        Book book1 = Book.builder().title("Effective Java").isbn("1000").authors("Joshua Bloch").numOfCopies(5).bookCategoryId(1).build();
        Book book2 = Book.builder().title("Core Java").isbn("1001").authors("Cay S.Horstmann").numOfCopies(10).bookCategoryId(1).build();
        Book book3 = Book.builder().title("Core Java").isbn("1002").authors("Herbert Schildt").numOfCopies(8).bookCategoryId(1).build();

        Book book4 = Book.builder().title("C# and .NET").isbn("1003").authors("Christian Nagel").numOfCopies(6).bookCategoryId(2).build();
        Book book5 = Book.builder().title("Pro C# 10 with .NET 6").isbn("1004").authors("Andrew Troelsen, Phil Japikse").numOfCopies(7).bookCategoryId(2).build();
        Book book6 = Book.builder().title(".NET Component").isbn("1005").authors("Juval Lowy").bookCategoryId(2).numOfCopies(9).build();

        bookRepository.save(book1);
        bookRepository.save(book2);
        bookRepository.save(book3);

        bookRepository.save(book4);
        bookRepository.save(book5);
        bookRepository.save(book6);

        Config config1 = Config.builder().itemName("Number of days is free to borrow").itemValue("30").build();
        Config config2 = Config.builder().itemName("Fine for overdue (per day)").itemValue("5").build();
        Config config3 = Config.builder().itemName("Maximum number of books can borrow").itemValue("3").build();
        Config config4 = Config.builder().itemName("Number of overdues to be suspended").itemValue("3").build();
        Config config5 = Config.builder().itemName("Number of overdues to be locked").itemValue("5").build();
        Config config6 = Config.builder().itemName("Number of suspended days").itemValue("15").build();
        Config config7 = Config.builder().itemName("Number of days to be reminded").itemValue("5").build();

        configRepository.save(config1);
        configRepository.save(config2);
        configRepository.save(config3);
        configRepository.save(config4);
        configRepository.save(config5);
        configRepository.save(config6);
        configRepository.save(config7);
    }
}
