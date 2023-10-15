package com.swe.lms.admin.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="LMS_CONFIG")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Config {
    @Id
    private Integer id;

    @NotNull
    private String itemName;

    @NotNull
    private String itemValue;

}
