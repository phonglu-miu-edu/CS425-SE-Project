package com.swe.lms.admin.api.service;

import com.swe.lms.admin.api.dto.BookCopyDTO;
import com.swe.lms.admin.api.dto.BookDTO;
import org.springframework.http.ResponseEntity;

public interface IBookCopyService {

    ResponseEntity<?> addBookCopies(BookDTO bookDTO);

    ResponseEntity<?> updateBookCopy(BookCopyDTO bookCopyDTO);
}
