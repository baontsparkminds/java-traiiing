package com.example.demo.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.ApplicantService;
import com.example.demo.service.dto.ApplicantDto;
import com.example.demo.service.dto.request.ApplicantRequestDto;
import com.lowagie.text.DocumentException;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/applicants")
@RequiredArgsConstructor
public class ApplicantController {

    private final ApplicantService applicantService;

    @GetMapping("")
    public ResponseEntity<List<ApplicantDto>> getApplicants() {
        return ResponseEntity.ok(applicantService.getApplicants());
    }

    @PostMapping("")
    public ResponseEntity<ApplicantDto> addApplicant(@RequestBody ApplicantRequestDto applicantRequestDto) {
        return ResponseEntity.ok(applicantService.addApplicant(applicantRequestDto));
    }

    @GetMapping("/export/pdf")
    public void exportToPDF(HttpServletResponse response) throws DocumentException, IOException {
        applicantService.exportToPdf(response).export(response);
    }

}
