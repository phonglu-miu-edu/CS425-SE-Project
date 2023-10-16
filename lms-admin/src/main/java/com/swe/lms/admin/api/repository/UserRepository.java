package com.swe.lms.admin.api.repository;

import com.swe.lms.admin.api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUserNameAndPassword(String userName, String password);
    Optional<User> findByUserName(String userName);

    List<User> findByUserNameLikeIgnoreCaseOrFirstNameLikeIgnoreCaseOrLastNameLikeIgnoreCaseOrEmailLikeIgnoreCaseOrPhoneNumberLikeIgnoreCaseOrAddressLikeIgnoreCaseOrRoleCdLikeIgnoreCaseOrStatusLikeIgnoreCase(String userName, String firstName, String lastName, String email, String phoneNumber, String address, String roleCd, String status);
}
