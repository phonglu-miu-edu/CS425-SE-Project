package com.swe.lms.admin.api.service.impl;

import com.swe.lms.admin.api.dto.UserDTO;
import com.swe.lms.admin.api.service.IUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.Map;

@Service("UserService")
public class UserServiceImpl implements IUserService  {
    @Override
    public ResponseEntity<?> insertUser(UserDTO userDTO) {
        return null;
    }

    @Override
    public ResponseEntity<?> updateUser(UserDTO userDTO) {
        return null;
    }

    @Override
    public ResponseEntity<?> deleteUser(int userId) {
        return null;
    }

    @Override
    public ResponseEntity<?> getUser(int userId) {
        return null;
    }

    @Override
    public ResponseEntity<?> searchUsers(Map<String, Object> mapParams) {
        return null;
    }

}
