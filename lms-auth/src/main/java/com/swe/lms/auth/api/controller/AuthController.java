package com.swe.lms.auth.api.controller;

import com.swe.lms.auth.api.aop.NoToken;
import com.swe.lms.auth.api.dto.UserDTO;
import com.swe.lms.auth.api.service.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletRequest;

@RestController
@RefreshScope
@RequestMapping("/lms/auth")
public class AuthController {

    @Autowired
    private IAuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(ServletRequest servReq, @RequestBody UserDTO userDTO) {
        return authService.login(servReq.getServerName(), userDTO);
    }

    @PostMapping("/logout")
    @NoToken
    public ResponseEntity<?> logout(ServletRequest servReq, @RequestBody UserDTO userDTO) {
        return authService.logout(servReq.getServerName(), userDTO);
    }

    @PostMapping("/verify")
    public ResponseEntity<?> verify(ServletRequest servReq, @RequestBody String token) {
        return authService.verify(servReq.getServerName(), token);
    }
}
