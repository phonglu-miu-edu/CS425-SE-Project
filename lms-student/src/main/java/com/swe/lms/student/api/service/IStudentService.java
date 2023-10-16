package com.swe.lms.student.api.service;

import com.swe.lms.student.api.dto.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface IStudentService {

    ResponseEntity<?> register(UserDTO userDTO);

    ResponseEntity<?> getUser(int userId);

    ResponseEntity<?> updateUser(UserDTO userDTO);

    ResponseEntity<?> getCheckoutRecords(int userId);
}
