package com.example.demo.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Applicant;
import com.example.demo.repository.ApplicantRepository;
import com.example.demo.service.ApplicantService;
import com.example.demo.service.dto.ApplicantDto;
import com.example.demo.service.dto.request.ApplicantRequestDto;
import com.example.demo.service.mapper.ApplicantMapper;
import com.example.demo.utils.ApplicantPDFExporter;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ApplicantServiceImpl implements ApplicantService {

    private final ApplicantRepository applicantRepository;
    private final ApplicantMapper applicantMapper;

    @Override
    public List<ApplicantDto> getApplicants() {
        List<Applicant> applicants = applicantRepository.findAll();
        return applicants.stream().map(applicantMapper::toDto).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ApplicantDto addApplicant(ApplicantRequestDto applicantRequestDto) {

        List<Applicant> applicants = applicantRepository.findByEmailAndIsDeletedFalse(applicantRequestDto.getEmail());

        applicants.stream().forEach(applicant -> {
            applicant.setDeleted(true);
        });

        applicantRepository.saveAll(applicants);

        return applicantMapper.toDto(applicantRepository.save(applicantMapper.toEntity(applicantRequestDto)));
    }

    @Override
    public ApplicantPDFExporter exportToPdf(HttpServletResponse response) {

        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        ApplicantPDFExporter exporter = new ApplicantPDFExporter(
                this.getApplicants().stream().map(applicantMapper::toEntity).collect(Collectors.toList()));

        return exporter;
    }

}
