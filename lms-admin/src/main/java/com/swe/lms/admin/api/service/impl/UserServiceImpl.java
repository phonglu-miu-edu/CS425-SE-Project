package com.swe.lms.admin.api.service.impl;

import com.swe.lms.admin.api.dto.UserDTO;
import com.swe.lms.admin.api.repository.BookRepository;
import com.swe.lms.admin.api.service.IUserService;
import com.swe.lms.admin.api.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
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
