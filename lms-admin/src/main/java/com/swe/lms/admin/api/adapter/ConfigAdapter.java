package com.swe.lms.admin.api.adapter;

import com.swe.lms.admin.api.dto.ConfigDTO;
import com.swe.lms.admin.api.model.Config;

public class ConfigAdapter {

    public static ConfigDTO convertToConfigDTO(Config config) {
        return ConfigDTO.builder().id(config.getId()).itemName(config.getItemName()).itemValue(config.getItemValue()).build();
    }

    public static Config convertToConfig(ConfigDTO configDTO) {
        return Config.builder().id(configDTO.getId()).itemName(configDTO.getItemName()).itemValue(configDTO.getItemValue()).build();
    }
}
