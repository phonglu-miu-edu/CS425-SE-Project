package com.swe.lms.book.api.feign;

import com.swe.lms.book.api.dto.BookCopyDTO;
import com.swe.lms.book.api.dto.ConfigDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value="AdminService")
public interface IAdminFeignClient {
    @GetMapping(value = "/lms/admin/books/search")
    ResponseEntity<?> searchBooks(@RequestParam(name="q") String keyword);

    @GetMapping(value = "/lms/admin/books/{bookId}")
    ResponseEntity<?> getBook(@PathVariable(name="bookId") int bookId);
    @GetMapping(value = "/lms/admin/configs")
    ResponseEntity<List<ConfigDTO>> getConfigs();

    @PutMapping("/lms/admin/book_copies")
    ResponseEntity<?> updateBookCopies(@RequestBody BookCopyDTO bookCopyDTO);
}
