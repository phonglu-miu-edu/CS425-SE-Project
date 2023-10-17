package com.swe.lms.book.api.repository;

import com.swe.lms.book.api.model.CheckoutRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CheckoutRepository extends JpaRepository<CheckoutRecord, Long> {
    List<CheckoutRecord> findByUserIdAndCheckinDateIsNull(Integer id);

    Integer countByUserIdAndCheckinDateIsNull(Integer userId);
}
