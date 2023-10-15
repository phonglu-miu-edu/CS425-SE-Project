package com.swe.lms.admin.api.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.swe.lms.admin.api.adapter.UserAdapter;
import com.swe.lms.admin.api.dto.UserDTO;
import com.swe.lms.admin.api.model.User;
import com.swe.lms.admin.api.repository.UserRepository;
import com.swe.lms.admin.api.service.IUserService;
import com.swe.lms.admin.api.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.Optional;

@Service("UserService")
public class UserServiceImpl implements IUserService  {

    @Autowired
    private UserRepository userRepository;

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
    public ResponseEntity<?> getLoginUser(UserDTO userDTO) {
        Optional<User> optUser = userRepository.findByUserNameAndPassword(userDTO.getUserName(), userDTO.getPassword());
        if (optUser.isPresent()) {
            return ResponseUtil.createOK(UserAdapter.convertToUserDTO(optUser.get()));
        }
        return ResponseUtil.createUnauthorize("Either user name or password is incorrect. Please try again.");
    }

    @Override
    public ResponseEntity<?> searchUsers(Map<String, Object> mapParams) {
        return null;
    }

}
