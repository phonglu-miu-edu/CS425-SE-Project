package com.swe.lms.student.api.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@FeignClient("BookService")
public interface IBookFeignClient {

    @GetMapping(value="/lms/book/checkout/records/{userId}")
    ResponseEntity<?> getCheckoutRecords(@PathVariable(name="userId") int userId);
}