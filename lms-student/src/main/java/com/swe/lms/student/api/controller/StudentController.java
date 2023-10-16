package com.swe.lms.student.api.controller;

import com.swe.lms.student.api.dto.UserDTO;
import com.swe.lms.student.api.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RefreshScope
@RequestMapping("/lms/student")
public class StudentController {
    @Autowired
    private IStudentService studentService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserDTO userDTO) {
        return studentService.register(userDTO);
    }

    @GetMapping("/students/{userId}")
    public ResponseEntity<?> getUser(@PathVariable(name="userId") int userId) {
        return studentService.getUser(userId);
    }

    @PutMapping("/students/{userId}")
    public ResponseEntity<?> updateUser(@PathVariable(name="userId") int userId, @RequestBody UserDTO userDTO) {
        userDTO.setId(userId);
        return studentService.updateUser(userDTO);
    }

    @GetMapping("/checkout_records/{userId}")
    public ResponseEntity<?> getCheckoutRecords(@PathVariable(name="userId") int userId) {
        return studentService.getCheckoutRecords(userId);
    }
}
