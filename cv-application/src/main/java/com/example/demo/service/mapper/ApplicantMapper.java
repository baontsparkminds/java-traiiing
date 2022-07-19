package com.example.demo.service.mapper;

import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.example.demo.entity.Applicant;
import com.example.demo.service.dto.ApplicantDto;
import com.example.demo.service.dto.request.ApplicantRequestDto;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ApplicantMapper {

    private final ProjectMapper projectMapper;

    public ApplicantDto toDto(Applicant applicant) {
        return ApplicantDto.builder()
                .id(applicant.getId())
                .email(applicant.getEmail())
                .github(applicant.getGithub())
                .projects(applicant.getProjects()
                        .stream().map(projectMapper::toDto).collect(Collectors.toList()))
                .build();
    }

    public Applicant toEntity(ApplicantRequestDto applicantRequestDto) {

        Applicant applicant = new Applicant();
        applicant.setEmail(applicantRequestDto.getEmail());
        applicant.setGithub(applicantRequestDto.getGithub());
        applicant.setProjects(applicantRequestDto.getProjects().stream().map(project -> {
            return projectMapper.toEntity(project, applicant);
        }).collect(Collectors.toList()));

        return applicant;
    }
    
    public Applicant toEntity(ApplicantDto applicantDto) {

        Applicant applicant = new Applicant();
        applicant.setEmail(applicantDto.getEmail());
        applicant.setGithub(applicantDto.getGithub());
        applicant.setProjects(applicantDto.getProjects().stream().map(project -> {
            return projectMapper.toEntity(project, applicant);
        }).collect(Collectors.toList()));

        return applicant;
    }
}
