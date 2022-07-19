package com.example.demo.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
    public ResponseEntity<?> exportToPDF() throws DocumentException {

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-type", MediaType.APPLICATION_PDF_VALUE);

        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
        headers.set("Content-Disposition", "attachment; filename=users_" + currentDateTime + ".pdf");

        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(applicantService.exportToPdf());

    }

}
