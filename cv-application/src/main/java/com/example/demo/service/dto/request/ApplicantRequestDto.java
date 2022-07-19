package com.example.demo.service.dto.request;

import java.util.List;

import com.example.demo.service.dto.ProjectDto;

import lombok.Data;

@Data
public class ApplicantRequestDto {
    private Long id;
    private String email;
    private String github;
    private List<ProjectDto> projects;
}
