package com.swe.lsm.auth.api.controller;

import com.swe.lsm.auth.api.dto.UserDTO;
import com.swe.lsm.auth.api.util.ResponseUtil;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class AuthController {

    @PostMapping("/lms/login")
    public ResponseEntity<?> login(UserDTO userDTO) {
        return ResponseUtil.createOK(null, "");
    }
}
