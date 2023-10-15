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
                .build();
    }
}
