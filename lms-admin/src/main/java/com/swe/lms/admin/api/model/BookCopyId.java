package com.swe.lms.admin.api.model;

import lombok.*;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BookCopyId implements Serializable {

    private Integer bookId;

    private Integer seq;
}
