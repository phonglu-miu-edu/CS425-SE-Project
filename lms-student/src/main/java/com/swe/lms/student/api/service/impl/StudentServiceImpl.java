package com.swe.lms.student.api.service.impl;

import com.swe.lms.student.api.dto.UserDTO;
import com.swe.lms.student.api.feign.IAdminFeignClient;
import com.swe.lms.student.api.feign.IBookFeignClient;
import com.swe.lms.student.api.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service("StudentService")
public class StudentServiceImpl implements IStudentService {

    @Autowired
    private IAdminFeignClient adminFeignClient;

    @Autowired
    private IBookFeignClient bookFeignClient;

    @Override
    public ResponseEntity<?> register(UserDTO userDTO) {
        return adminFeignClient.insertUser(userDTO);
    }

    @Override
    public ResponseEntity<?> getUser(int userId) {
        return adminFeignClient.getUser(userId);
    }

    @Override
    public ResponseEntity<?> updateUser(UserDTO userDTO) {
        return adminFeignClient.updateUser(userDTO.getId(), userDTO);
    }

    @Override
    public ResponseEntity<?> getCheckoutRecords(int userId) {
        return bookFeignClient.getCheckoutRecords(userId);
    }
}
