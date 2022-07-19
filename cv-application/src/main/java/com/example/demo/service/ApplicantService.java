package com.example.demo.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.example.demo.service.dto.ApplicantDto;
import com.example.demo.service.dto.request.ApplicantRequestDto;
import com.example.demo.utils.ApplicantPDFExporter;

public interface ApplicantService {

    List<ApplicantDto> getApplicants();

    ApplicantDto addApplicant(ApplicantRequestDto applicantRequestDto);

    ApplicantPDFExporter exportToPdf(HttpServletResponse response);
}
