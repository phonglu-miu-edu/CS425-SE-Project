package com.swe.lms.book.api.feign;

import com.swe.lms.book.api.dto.BookDTO;
import com.swe.lms.book.api.model.Book;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@FeignClient(value="AdminService",url="http://localhost:8083/lms/admin")
public interface IAdminFeignClient {
//    @GetMapping(value = "/lms/admin/books/search")
//    ResponseEntity<?> searchBooks(@RequestParam(name="q") String keyword);
    @GetMapping(value = "/books/{bookId}")
    ResponseEntity<?> getBook(@PathVariable(name="bookId") int bookId);
}
