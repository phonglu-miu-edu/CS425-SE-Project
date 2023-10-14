package com.swe.lms.book.controller;

import com.swe.lms.book.dto.BookDTO;
import com.swe.lms.book.model.Book;
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
@RequestMapping("/lms/book")
public class BookController {
    @Autowired
    BookRepository repository;

    @PostMapping("/add")
    public ResponseEntity<?> addBook(@RequestBody BookDTO bookDTO) {
        try {
            Book book = repository.save(new Book(bookDTO.id, bookDTO.title, bookDTO.isbn));

            Map<String, Object> map = new HashMap<>();
            map.put(HTTPConst.STATUS_CODE, HttpStatus.OK.value());
            map.put(HTTPConst.MESSAGE, "Book created");
            map.put(HTTPConst.DATA, book);
            return new ResponseEntity<>(map, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/books")
    public ResponseEntity<List<Book>> getAllBooks(@RequestParam(required = false) String title) {
        try {
            List<Book> books = new ArrayList<Book>();

            if (title == null)
                repository.findAll().forEach(books::add);
            else
                repository.findByTitleContaining(title).forEach(books::add);

            if (books.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(books, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable("id") Integer id) {
        Optional<Book> result = repository.findById(id);

        if (result.isPresent()) {
            return new ResponseEntity<>(result.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable("id") Integer id, @RequestBody BookDTO bookDTO) {
        Optional<Book> result = repository.findById(id);

        if (result.isPresent()) {
            Book _book = result.get();
            _book.setTitle(bookDTO.title);
            _book.setISBN(bookDTO.isbn);
            return new ResponseEntity<>(repository.save(_book), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

//not working
//    @DeleteMapping("/delete/{id}")
//    public ResponseEntity<HttpStatus> deleteBook(@PathVariable("id") long id) {
//        try {
//            repository.deleteById(id);
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

    @DeleteMapping("/delete")
    public ResponseEntity<HttpStatus> deleteAllBooks() {
        try {
            repository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/checkout")
    public ResponseEntity<?> checkoutBook(@RequestBody BookDTO bookDTO) {
        try {
            List<Book> books = new ArrayList<Book>();
            repository.findByTitleContaining(bookDTO.title).forEach(books::add);

            if (books.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            Book result = books.get(0);
            result.setTitle(result.getTitle() + " checked out on " + new Date());

            Book book = repository.save(result);

            Map<String, Object> map = new HashMap<>();
            map.put(HTTPConst.STATUS_CODE, HttpStatus.OK.value());
            map.put(HTTPConst.MESSAGE, "Book Checked out");
            map.put(HTTPConst.DATA, book);
            return new ResponseEntity<>(map, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
