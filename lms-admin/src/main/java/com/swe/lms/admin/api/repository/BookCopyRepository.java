package com.swe.lms.admin.api.repository;

import com.swe.lms.admin.api.model.BookCopy;
import com.swe.lms.admin.api.model.BookCopyId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookCopyRepository extends JpaRepository<BookCopy, BookCopyId> {

}