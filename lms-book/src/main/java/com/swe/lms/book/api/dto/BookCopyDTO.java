package com.swe.lms.book.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookCopyDTO
{
    private BookCopyIdDTO copyId;

    private String status;

    private String statusDetail;
}
