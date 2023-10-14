package com.swe.lsm.auth.api.adapter;

import com.swe.lsm.auth.api.dto.UserDTO;
import com.swe.lsm.auth.api.model.User;

public class UserAdapter {
    public static UserDTO convertToUserDTO(User user) {
        return UserDTO.builder().id(user.getId()).userName(user.getUserName()).roleCd(user.getRoleCd()).build();
    }
}
