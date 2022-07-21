package com.example.demo.service.impl;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.config.JwtProvider;
import com.example.demo.constant.Constant;
import com.example.demo.entity.User;
import com.example.demo.exception.DataExistedException;
import com.example.demo.exception.DataNotFoundException;
import com.example.demo.exception.SigninFailException;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.AuthenticateService;
import com.example.demo.service.dto.UserDto;
import com.example.demo.service.dto.reponse.SigninResponseDto;
import com.example.demo.service.dto.request.SigninRequestDto;
import com.example.demo.service.dto.request.SignupRequestDto;
import com.example.demo.service.mapper.UserMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthenticateServiceImpl implements AuthenticateService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider tokenProvider;

    @Override
    public SigninResponseDto signin(SigninRequestDto requestDto) {
        Optional<User> user = userRepository.findByEmailAndIsDeletedFalse(requestDto.getEmail());

        if (!user.isPresent()) {
            throw new DataNotFoundException(Constant.MESSAGE_EMAIL_NOT_FOUND, Constant.CODE_NOT_FOUND);
        }

        if (!passwordEncoder.matches(requestDto.getPassword(), user.get().getPassword())) {
            throw new SigninFailException(Constant.MESSAGE_INVALID_PASSWORD, Constant.CODE_INVALID_PASSWORD);
        }

        String jwt = tokenProvider.generateToken(userMapper.toDto(user.get()));

        SigninResponseDto response = new SigninResponseDto();

        response.setSessionId(jwt);

        return response;
    }

    @Override
    @Transactional
    public UserDto signup(SignupRequestDto requestDto) {
        Optional<User> user = userRepository.findByEmail(requestDto.getEmail());

        if (user.isPresent()) {
            throw new DataExistedException(Constant.MESSAGE_EMAIL_EXISTED, Constant.CODE_EXISTED);
        }

        requestDto.setPassword(passwordEncoder.encode(requestDto.getPassword()));

        return userMapper.toDto(userRepository.save(userMapper.toEntity(requestDto)));
    }
}
