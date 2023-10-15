package com.swe.lms.auth.api.feign;

import com.swe.lms.auth.api.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@FeignClient("AdminService")
public interface IUserFeignClient {
    @PostMapping(value = "/lms/admin/users/info", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Map<String, Object>> getLoginUser(@RequestBody UserDTO userDTO);
}
