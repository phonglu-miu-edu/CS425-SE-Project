package com.swe.lms.student.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private Integer id;

    private String userName;

    private String password;

    private String roleCd;

    private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;

    private String address;

    private String status;

    private Integer numOfOverdues;
}
