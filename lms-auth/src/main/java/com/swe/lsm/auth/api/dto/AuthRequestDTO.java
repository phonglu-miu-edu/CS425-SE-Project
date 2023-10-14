package com.swe.lsm.auth.api.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthRequestDTO {

    private String token;

    private String domain;
}
