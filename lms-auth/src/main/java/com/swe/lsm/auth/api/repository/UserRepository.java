package com.swe.lsm.auth.api.repository;

import com.swe.lsm.auth.api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUserNameAndPassword(String userName, String password);
}
