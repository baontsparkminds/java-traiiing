package com.example.demo.service.dto;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApplicantDto {

    private Long id;
    private String email;
    private String github;
    private List<ProjectDto> projects;
}
