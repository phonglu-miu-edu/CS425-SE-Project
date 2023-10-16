package com.swe.lms.admin.api.adapter;

import com.swe.lms.admin.api.dto.BookCopyDTO;
import com.swe.lms.admin.api.dto.BookCopyIdDTO;
import com.swe.lms.admin.api.model.BookCopy;
import com.swe.lms.admin.api.model.BookCopyId;

public class BookCopyAdapter {
    public static BookCopyDTO convertToBookCopyDTO(BookCopy bookCopy) {
        BookCopyIdDTO bookCopyIdDTO = BookCopyIdDTO.builder().bookId(bookCopy.getCopyId().getBookId()).seq(bookCopy.getCopyId().getSeq()).build();
        return BookCopyDTO.builder()
                .copyId(bookCopyIdDTO)
                .status(bookCopy.getStatus())
                .statusDetail(bookCopy.getStatusDetail())
                .build();
    }

    public static BookCopy convertToBookCopy(BookCopyDTO bookCopyDTO) {
        BookCopyId bookCopyId = BookCopyId.builder().bookId(bookCopyDTO.getCopyId().getBookId()).seq(bookCopyDTO.getCopyId().getSeq()).build();
        return BookCopy.builder()
                .copyId(bookCopyId)
                .status(bookCopyDTO.getStatus())
                .statusDetail(bookCopyDTO.getStatusDetail())
                .build();
    }
}
