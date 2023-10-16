package com.swe.lms.book.api.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient("AdminService")
public interface IAdminFeignClient {
    @GetMapping(value = "/lms/admin/books/search")
    ResponseEntity<?> searchBooks(@RequestParam(name="q") String keyword);
}