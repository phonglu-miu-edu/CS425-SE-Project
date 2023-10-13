package com.swe.lsm.auth.api.service;

import org.springframework.http.ResponseEntity;

public interface IUserService {
    ResponseEntity<?> verify(String domain, String token);
}
