package com.swe.lms.student.api.feign;

import com.swe.lms.student.api.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient("AdminService")
public interface IAdminFeignClient {

    @PostMapping(value = "/lsm/admin/users")
    ResponseEntity<?> insertUser(@RequestBody UserDTO userDTO);

    @GetMapping(value= "/lsm/admin/users/{userId}")
    ResponseEntity<?> getUser(@PathVariable(name="userId") int userId);

    @PutMapping(value="/lsm/admin/users/{userId}")
    ResponseEntity<?> updateUser(@PathVariable(name="userId") int userId, @RequestBody UserDTO userDTO);
}