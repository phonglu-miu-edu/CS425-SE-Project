package com.swe.lms.admin.api.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="BookCopy")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookCopy {
    @EmbeddedId
    private BookCopyId copyId;

    @NotNull
    private String status;

    @NotNull
    private String statusDetail;
}
