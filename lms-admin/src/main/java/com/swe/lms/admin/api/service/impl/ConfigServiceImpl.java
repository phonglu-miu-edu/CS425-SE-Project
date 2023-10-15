package com.swe.lms.admin.api.service.impl;

import com.swe.lms.admin.api.adapter.ConfigAdapter;
import com.swe.lms.admin.api.dto.ConfigDTO;
import com.swe.lms.admin.api.model.Config;
import com.swe.lms.admin.api.repository.ConfigRepository;
import com.swe.lms.admin.api.service.IConfigService;
import com.swe.lms.admin.api.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service("ConfigService")
public class ConfigServiceImpl implements IConfigService {

    @Autowired
    private ConfigRepository configRepository;

    @Override
    public ResponseEntity<?> getConfigs() {
        List<Config> configs = configRepository.findAll();
        if (null != configs && configs.size() > 0) {
            List<ConfigDTO> configDTOs = configs.stream().map(cfg -> ConfigAdapter.convertToConfigDTO(cfg)).collect(Collectors.toList());
            return ResponseUtil.createOK(configDTOs);
        }
        return ResponseUtil.createOK(new ArrayList<>());
    }

    @Override
    public ResponseEntity<?> updateConfigs(List<ConfigDTO> configDTOs) {
        if (null != configDTOs && configDTOs.size() > 0) {
            configDTOs.forEach(cfgDTO -> {
                Config config = ConfigAdapter.convertToConfig(cfgDTO);
                configRepository.save(config);
            });
        }
        return getConfigs();
    }
}
