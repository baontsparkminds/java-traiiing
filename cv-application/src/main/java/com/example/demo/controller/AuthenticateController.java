package com.example.demo.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.AuthenticateService;
import com.example.demo.service.dto.UserDto;
import com.example.demo.service.dto.reponse.SigninResponseDto;
import com.example.demo.service.dto.request.SigninRequestDto;
import com.example.demo.service.dto.request.SignupRequestDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/authenticate")
@RequiredArgsConstructor
public class AuthenticateController {

    private final AuthenticateService authenticateService;

    @PostMapping("/signin")
    public ResponseEntity<SigninResponseDto> signin(@RequestBody SigninRequestDto requestDto) {
        return ResponseEntity.ok(authenticateService.signin(requestDto));
    }

    @PostMapping("/signup")
    public ResponseEntity<UserDto> signup(@Valid @RequestBody SignupRequestDto requestDto) {
        return ResponseEntity.ok(authenticateService.signup(requestDto));
    }
}
