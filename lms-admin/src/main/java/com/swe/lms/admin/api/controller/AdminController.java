package com.swe.lms.admin.api.controller;

import com.swe.lms.admin.api.aop.NoToken;
import com.swe.lms.admin.api.constant.LmsConst;
import com.swe.lms.admin.api.dto.*;
import com.swe.lms.admin.api.service.*;
import com.swe.lms.admin.api.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RefreshScope
@RequestMapping("/lms/admin")
public class AdminController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IBookService bookService;

    @Autowired
    private IBookCategoryService bookCategoryService;

    @Autowired
    private IConfigService configService;

    @Autowired
    private IBookCopyService bookCopyService;

    //-------------- Users ----------------------

    @PostMapping("/users")
    public ResponseEntity<?> insertUser(@RequestBody UserDTO userDTO) {
        return userService.insertUser(userDTO);
    }

    @PutMapping("/users/{userId}")
    public ResponseEntity<?> updateUser(@PathVariable(name="userId") int userId, @RequestBody UserDTO userDTO) {
        userDTO.setId(userId);
        return userService.updateUser(userDTO);
    }

    @DeleteMapping("/users/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable(name="userId") int userId) {
        return userService.deleteUser(userId);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<?> getUser(@PathVariable(name="userId") int userId) {
        return userService.getUser(userId);
    }

    @PostMapping("/users/info")
    @NoToken
    public ResponseEntity<?> getLoginUser(@RequestBody UserDTO userDTO) {
        return userService.getLoginUser(userDTO);
    }

    @GetMapping("/users/search")
    public ResponseEntity<?> searchUsers(@RequestParam(name="q") String keyword) {
        return userService.searchUsers(keyword);
    }

    //-------------- Books ----------------------

    @PostMapping("/books")
    public ResponseEntity<?> insertBook(@RequestBody BookDTO bookDTO) {
        return bookService.insertBook(bookDTO);
    }

    @PutMapping("/books/{bookId}")
    public ResponseEntity<?> updateBook(@PathVariable(name="bookId") int bookId, @RequestBody BookDTO bookDTO) {
        bookDTO.setId(bookId);
        return bookService.updateBook(bookDTO);
    }

    @DeleteMapping("/books/{bookId}")
    public ResponseEntity<?> deleteBook(@PathVariable(name="bookId") int bookId) {
        return bookService.deleteBook(bookId);
    }

    @GetMapping("/books/{bookId}")
    public ResponseEntity<?> getBook(@PathVariable(name="bookId") int bookId) {
        return bookService.getBook(bookId);
    }

    @GetMapping("/books/search")
    public ResponseEntity<?> searchBooks(@RequestParam(name="q") String keyword) {
        return bookService.searchBooks(keyword);
    }

    //-------------- Book Categories ----------------------

    @PostMapping("/book_categories")
    public ResponseEntity<?> insertBookCategory(@RequestBody BookCategoryDTO bookCategoryDTO) {
        return bookCategoryService.insertBookCategory(bookCategoryDTO);
    }

    @PutMapping("/book_categories/{bookCategoryId}")
    public ResponseEntity<?> updateBook(@PathVariable(name="bookCategoryId") int bookCategoryId, @RequestBody BookCategoryDTO bookCategoryDTO) {
        bookCategoryDTO.setId(bookCategoryId);
        return bookCategoryService.updateBookCategory(bookCategoryDTO);
    }

    @DeleteMapping("/book_categories/{bookId}")
    public ResponseEntity<?> deleteBookCategory(@PathVariable(name="bookId") int bookCategoryId) {
        return bookCategoryService.deleteBookCategory(bookCategoryId);
    }

    @GetMapping("/book_categories/{bookId}")
    public ResponseEntity<?> getBookCagegory(@PathVariable(name="bookId") int bookCategoryId) {
        return bookCategoryService.getBookCategory(bookCategoryId);
    }

    @GetMapping("/book_categories/search")
    public ResponseEntity<?> searchBookCategories(@RequestParam(name="q") String keyword) {
        return bookCategoryService.searchBookCategories(keyword);
    }

    //-------------- Configuration ----------------------
    @GetMapping("/configs")
    public ResponseEntity<?> getConfigs() {
        return configService.getConfigs();
    }

    @PutMapping("/configs")
    public ResponseEntity<?> updateConfigs(@RequestBody List<ConfigDTO> configDTOs) {
        return configService.updateConfigs(configDTOs);
    }

    //-------------- Book Copy ----------------------

    @PostMapping("/book_copies")
    public ResponseEntity<?> addBookCopies(@RequestBody BookDTO bookDTO) {
        return bookCopyService.addBookCopies(bookDTO);
    }

    @PutMapping("/book_copies")
    public ResponseEntity<?> updateBookCopies(@RequestBody BookCopyDTO bookCopyDTO) {
        return bookCopyService.updateBookCopy(bookCopyDTO);
    }

    @GetMapping("/book_copies/{bookId}")
    public ResponseEntity<?> getBookCopies(@PathVariable(name="bookId") int bookId) {
        return bookCopyService.getBookCopies(bookId);
    }
}
