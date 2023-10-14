package com.swe.lsm.auth.api.repository;

import com.swe.lsm.auth.api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Integer, User> {
}
