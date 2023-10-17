package com.swe.lms.book.api.feign;

import com.swe.lms.book.api.dto.BookCopyDTO;
import com.swe.lms.book.api.dto.ConfigDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value="AdminService",url="http://localhost:8083/lms/admin")
public interface IAdminFeignClient {
    @GetMapping(value = "/lms/admin/books/search")
    ResponseEntity<?> searchBooks(@RequestParam(name="q") String keyword);

    @GetMapping(value = "/lsm/admin/books/{bookId}")
    ResponseEntity<?> getBook(@PathVariable(name="bookId") int bookId);
    @GetMapping(value = "/lsm/admin/configs")
    ResponseEntity<List<ConfigDTO>> getConfigs();

    @PutMapping("/lsm/admin/book_copies")
    ResponseEntity<?> updateBookCopies(@RequestBody BookCopyDTO bookCopyDTO);
}
