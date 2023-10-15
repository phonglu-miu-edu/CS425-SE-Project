package com.swe.lms.admin.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="LMS_CONFIG")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Config {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull
    private String itemName;

    @NotNull
    private String itemValue;

}
