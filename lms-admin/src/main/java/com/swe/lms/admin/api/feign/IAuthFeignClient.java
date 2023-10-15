package com.swe.lms.admin.api.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@FeignClient("AuthService")
public interface IAuthFeignClient {
    @PostMapping(value = "/lms/auth/verify")
    ResponseEntity<Map<String, Object>> verify(@RequestBody String token);
}