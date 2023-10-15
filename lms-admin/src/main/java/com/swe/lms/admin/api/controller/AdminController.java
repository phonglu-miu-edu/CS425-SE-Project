package com.swe.lms.admin.api.controller;

import com.swe.lms.admin.api.aop.NoToken;
import com.swe.lms.admin.api.constant.LmsConst;
import com.swe.lms.admin.api.dto.BookCategoryDTO;
import com.swe.lms.admin.api.dto.BookDTO;
import com.swe.lms.admin.api.dto.ConfigDTO;
import com.swe.lms.admin.api.dto.UserDTO;
import com.swe.lms.admin.api.service.IBookCategoryService;
import com.swe.lms.admin.api.service.IBookService;
import com.swe.lms.admin.api.service.IConfigService;
import com.swe.lms.admin.api.service.IUserService;
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
    public ResponseEntity<?> searchUsers(@RequestParam(name="q") String strQuery) {
        Map<String, Object> mapParams = new HashMap<>(StringUtil.parseQueryParam(strQuery, LmsConst.PARAM_DELIM));
        return userService.searchUsers(mapParams);
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
    public ResponseEntity<?> searchBooks(@RequestParam(name="q") String strQuery) {
        Map<String, Object> mapParams = new HashMap<>(StringUtil.parseQueryParam(strQuery, LmsConst.PARAM_DELIM));
        return bookService.searchBooks(mapParams);
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
    public ResponseEntity<?> searchBookCategories(@RequestParam(name="q") String strQuery) {
        Map<String, Object> mapParams = new HashMap<>(StringUtil.parseQueryParam(strQuery, LmsConst.PARAM_DELIM));
        return bookCategoryService.searchBookCategories(mapParams);
    }

    @GetMapping("/configs")
    public ResponseEntity<?> getConfigs() {
        return configService.getConfigs();
    }
    @PutMapping("/configs")
    public ResponseEntity<?> updateConfigs(@RequestBody List<ConfigDTO> configDTOs) {
        return configService.updateConfigs(configDTOs);
    }
}
