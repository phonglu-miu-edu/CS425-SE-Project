package com.swe.lms.admin.api.service;

import com.swe.lms.admin.api.dto.UserDTO;
import org.springframework.http.ResponseEntity;
import java.util.Map;

public interface IUserService {

    ResponseEntity<?> insertUser(UserDTO userDTO);

    ResponseEntity<?> updateUser(UserDTO userDTO);

    ResponseEntity<?> deleteUser(int userId);

    ResponseEntity<?> getUser(int userId);

    ResponseEntity<?> searchUsers(Map<String, Object> mapParams);
}
