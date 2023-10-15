package com.swe.lms.admin.api.adapter;

import com.swe.lms.admin.api.dto.UserDTO;
import com.swe.lms.admin.api.model.User;

public class UserAdapter {
    public static UserDTO convertToUserDTO(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .userName(user.getUserName())
                .roleCd(user.getRoleCd())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .address(user.getAddress())
                .status(user.getStatus())
                .numOfOverdues(user.getNumOfOverdues())
                .build();
    }

    public static User convertToUser(UserDTO userDTO) {
        return User.builder()
                .id(userDTO.getId())
                .userName(userDTO.getUserName())
                .password(userDTO.getPassword())
                .roleCd(userDTO.getRoleCd())
                .firstName(userDTO.getFirstName())
                .lastName(userDTO.getLastName())
                .address(userDTO.getAddress())
                .phoneNumber(userDTO.getPhoneNumber())
                .email(userDTO.getEmail())
                .status(userDTO.getStatus())
                .numOfOverdues(userDTO.getNumOfOverdues())
                .build();
    }
}
