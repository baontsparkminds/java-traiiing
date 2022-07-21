package com.example.demo.service;

import com.example.demo.service.dto.UserDto;
import com.example.demo.service.dto.reponse.SigninResponseDto;
import com.example.demo.service.dto.request.SigninRequestDto;
import com.example.demo.service.dto.request.SignupRequestDto;

public interface AuthenticateService {

    SigninResponseDto signin(SigninRequestDto requestDto);

    UserDto signup(SignupRequestDto requestDto);

}
