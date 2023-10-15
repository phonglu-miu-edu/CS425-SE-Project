package com.swe.lms.admin.api.service;

import com.swe.lms.admin.api.dto.ConfigDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IConfigService {

    ResponseEntity<?> getConfigs();

    ResponseEntity<?> updateConfigs(List<ConfigDTO> configDTOs);
}
