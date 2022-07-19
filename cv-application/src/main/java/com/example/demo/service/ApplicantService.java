package com.example.demo.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import com.example.demo.service.dto.ApplicantDto;
import com.example.demo.service.dto.request.ApplicantRequestDto;
import com.lowagie.text.DocumentException;

public interface ApplicantService {

	List<ApplicantDto> getApplicants();

	ApplicantDto addApplicant(ApplicantRequestDto applicantRequestDto);

	byte[] exportToPdf() throws DocumentException, MalformedURLException, IOException;
}
