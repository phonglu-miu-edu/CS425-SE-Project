package com.swe.lms.book.controller;

import com.swe.lms.book.dto.BookCategoryDTO;
import com.swe.lms.book.dto.BookDTO;
import com.swe.lms.book.model.Book;
import com.swe.lms.book.model.BookCategory;
import com.swe.lms.book.repository.BookCategoryRepository;
import com.swe.lms.book.repository.BookRepository;
import com.swe.lsm.auth.api.constant.HTTPConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RefreshScope
@RequestMapping("/lms/bookCategory")
public class BookCategoryController {
    @Autowired
    BookCategoryRepository repository;

    @PostMapping("/add")
    public ResponseEntity<?> addBookCategory(@RequestBody BookCategoryDTO bookCategoryDTO) {
        try {
            BookCategory bookCategory = repository.save(new BookCategory(bookCategoryDTO.categoryName, bookCategoryDTO.description));

            Map<String, Object> map = new HashMap<>();
            map.put(HTTPConst.STATUS_CODE, HttpStatus.OK.value());
            map.put(HTTPConst.MESSAGE, "Book category created");
            map.put(HTTPConst.DATA, bookCategory);
            return new ResponseEntity<>(map, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/categories")
    public ResponseEntity<List<BookCategory>> getAllBooks(@RequestParam(required = false) String categoryName) {
        try {
            List<BookCategory> bookCategories = new ArrayList<BookCategory>();

            if (categoryName == null)
                repository.findAll().forEach(bookCategories::add);
            else
                repository.findByCategoryNameContaining(categoryName).forEach(bookCategories::add);

            if (bookCategories.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(bookCategories, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookCategory> getBookCategoryById(@PathVariable("id") Integer id) {
        Optional<BookCategory> result = repository.findById(id);

        if (result.isPresent()) {
            return new ResponseEntity<>(result.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookCategory> updateBookCategory(@PathVariable("id") Integer id, @RequestBody BookCategoryDTO bookCategoryDTO) {
        Optional<BookCategory> result = repository.findById(id);

        if (result.isPresent()) {
            BookCategory _bookCategory = result.get();
            _bookCategory.setCategoryName(bookCategoryDTO.categoryName);

            return new ResponseEntity<>(repository.save(_bookCategory), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

//not working
//    @DeleteMapping("/delete/{id}")
//    public ResponseEntity<HttpStatus> deleteBookCategory(@PathVariable("id") long id) {
//        try {
//            repository.deleteById(id);
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

    @DeleteMapping("/delete")
    public ResponseEntity<HttpStatus> deleteAllBookCategories() {
        try {
            repository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
