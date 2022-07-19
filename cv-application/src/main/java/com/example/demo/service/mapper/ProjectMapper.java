package com.example.demo.service.mapper;

import org.springframework.stereotype.Component;

import com.example.demo.entity.Applicant;
import com.example.demo.entity.Project;
import com.example.demo.service.dto.ProjectDto;

@Component
public class ProjectMapper {

    public ProjectDto toDto(Project project) {
        return ProjectDto.builder()
            .id(project.getId())
            .name(project.getName())
            .employmentMode(project.getEmploymentMode())
            .capacity(project.getCapacity())
            .durationInMonths(project.getDurationInMonths())
            .startYear(project.getStartYear())
            .role(project.getRole())
            .teamSize(project.getTeamSize())
            .repositoryUrl(project.getRepositoryUrl())
            .liveUrl(project.getLiveUrl())
            .build();
    }

    public Project toEntity(ProjectDto projectDto, Applicant applicant) {

        Project project = new Project();

        project.setId(projectDto.getId());
        project.setName(projectDto.getName());
        project.setEmploymentMode(projectDto.getEmploymentMode());
        project.setCapacity(projectDto.getCapacity());
        project.setDurationInMonths(projectDto.getDurationInMonths());
        project.setStartYear(projectDto.getStartYear());
        project.setRole(projectDto.getRole());
        project.setTeamSize(projectDto.getTeamSize());
        project.setRepositoryUrl(projectDto.getRepositoryUrl());
        project.setLiveUrl(projectDto.getLiveUrl());
        project.setApplicant(applicant);

        return project;
    }
}
