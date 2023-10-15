package com.swe.lms.admin.api.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("AuthService")
public interface IAuthFeignClient {
    @PostMapping(value = "/lms/auth/verify")
    ResponseEntity<?> verify(@RequestBody String token);
}