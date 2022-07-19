package com.example.demo.service.dto;

import java.math.BigDecimal;

import com.example.demo.entity.enums.Capacity;
import com.example.demo.entity.enums.EmploymentMode;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProjectDto {

    private Long id;
    private String name;
    private EmploymentMode employmentMode;
    private Capacity capacity;
    private BigDecimal durationInMonths;
    private Integer startYear;
    private String role;
    private Integer teamSize;
    private String repositoryUrl;
    private String liveUrl;
}
