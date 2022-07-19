package com.example.demo.service;

import java.util.List;

import com.example.demo.service.dto.ApplicantDto;
import com.example.demo.service.dto.request.ApplicantRequestDto;

public interface ApplicantService {

    List<ApplicantDto> getApplicants();

    ApplicantDto addApplicant(ApplicantRequestDto applicantRequestDto);

    byte[] exportToPdf();
}
