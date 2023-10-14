package com.swe.lms.book.controller;

import com.swe.lms.book.bookDTO.BookDTO;
import com.swe.lsm.auth.api.constant.HTTPConst;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RefreshScope
public class BookController {
    @PostMapping("/lms/addbook")
    public ResponseEntity<?> login(BookDTO bookDTO) {
        Map<String, Object> map = new HashMap<>();
        map.put(HTTPConst.STATUS_CODE, HttpStatus.OK.value());
        map.put(HTTPConst.MESSAGE, "message");
        map.put(HTTPConst.DATA, bookDTO);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
