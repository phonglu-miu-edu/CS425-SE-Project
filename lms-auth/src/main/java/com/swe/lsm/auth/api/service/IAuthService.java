package com.swe.lsm.auth.api.service;

import com.swe.lsm.auth.api.dto.UserDTO;
import org.springframework.http.ResponseEntity;

public interface IAuthService {
    ResponseEntity<?> verify(String domain, String token);

    ResponseEntity<?> login(String domain, UserDTO userDTO);

    ResponseEntity<?> logout(String domain, UserDTO userDTO);
}
