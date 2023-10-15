package com.swe.lms.admin.api.service.impl;

import com.swe.lms.admin.api.adapter.UserAdapter;
import com.swe.lms.admin.api.constant.UserStatus;
import com.swe.lms.admin.api.dto.UserDTO;
import com.swe.lms.admin.api.model.User;
import com.swe.lms.admin.api.repository.UserRepository;
import com.swe.lms.admin.api.service.IUserService;
import com.swe.lms.admin.api.util.ExceptionUtil;
import com.swe.lms.admin.api.util.ResponseUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.Optional;

@Service("UserService")
public class UserServiceImpl implements IUserService  {

    private final Logger log = LoggerFactory.getLogger(getClass());
    @Autowired
    private UserRepository userRepository;

    @Override
    public ResponseEntity<?> insertUser(UserDTO userDTO) {
        try {
            String userName = userDTO.getUserName();
            if (StringUtils.isBlank(userName)) {
                return ResponseUtil.createBadRequest("Username can't be empty");
            }
            Optional<User> optUser = userRepository.findByUserName(userName);
            if (optUser.isPresent()) {
                return ResponseUtil.createBadRequest(String.format("Username [%s] is already existed", userName));
            }
            User user = UserAdapter.convertToUser(userDTO);
            user.setStatus(UserStatus.ACTIVE.getValue());
            user.setNumOfOverdues(0);
            user = userRepository.save(user);
            return ResponseUtil.createOK(UserAdapter.convertToUserDTO(user), "User is created successfully.");
        } catch (Exception ex) {
            log.error(ExceptionUtil.getStackTrace(ex));
            return ResponseUtil.createInternalServerError("Insert user failed");
        }
    }

    @Override
    public ResponseEntity<?> updateUser(UserDTO userDTO) {
        Optional<User> optUser = userRepository.findById(userDTO.getId());
        if (optUser.isPresent()) {
            User user = UserAdapter.convertToUser(userDTO);
            userRepository.save(user);
            return ResponseUtil.createOK(userDTO, "Updated user information successfully.");
        }
        return ResponseUtil.createNotFound(String.format("User ID [%d] is not found.", userDTO.getId()));
    }

    @Override
    public ResponseEntity<?> deleteUser(int userId) {
        Optional<User> optUser = userRepository.findById(userId);
        if (!optUser.isPresent()) {
            return ResponseUtil.createNotFound(String.format("User ID [%d] is not found.", userId));
        }
        userRepository.deleteById(userId);
        return ResponseUtil.createOK(String.format("Deleted user ID [%d] successfully.", userId));
    }

    @Override
    public ResponseEntity<?> getUser(int userId) {
        Optional<User> optUser = userRepository.findById(userId);
        if (!optUser.isPresent()) {
            return ResponseUtil.createNotFound(String.format("User ID [%d] is not found.", userId));
        }
        return ResponseUtil.createOK(UserAdapter.convertToUserDTO(optUser.get()));
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
        return ResponseUtil.createOK("OK");
    }

}
