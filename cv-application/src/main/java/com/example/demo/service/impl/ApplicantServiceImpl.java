package com.example.demo.service.impl;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Applicant;
import com.example.demo.exception.DataNotFoundException;
import com.example.demo.repository.ApplicantRepository;
import com.example.demo.service.ApplicantService;
import com.example.demo.service.dto.ApplicantDto;
import com.example.demo.service.dto.request.ApplicantRequestDto;
import com.example.demo.service.mapper.ApplicantMapper;
import com.example.demo.utils.ApplicantPDFExporter;
import com.lowagie.text.DocumentException;

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
    public byte[] exportToPdf() throws DocumentException, MalformedURLException, IOException {

        return ApplicantPDFExporter
                .export(this.getApplicants().stream().map(applicantMapper::toEntity).collect(Collectors.toList()));
    }

    @Override
    public ApplicantDto getApplicant(Long id) {
        Optional<Applicant> applicant = applicantRepository.findById(id);
        if (!applicant.isPresent()) {
            throw new DataNotFoundException("Applicant not found.", "error.notfound");
        }
        return applicantMapper.toDto(applicant.get());
    }

}
