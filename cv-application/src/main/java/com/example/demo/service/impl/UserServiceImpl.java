package com.example.demo.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.constant.Constant;
import com.example.demo.entity.User;
import com.example.demo.exception.DataNotFoundException;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import com.example.demo.service.dto.UserDto;
import com.example.demo.service.mapper.UserMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserDto getUserById(Long userId) {
        // TODO Auto-generated method stub
        Optional<User> user = userRepository.findById(userId);

        if (!user.isPresent()) {
            throw new DataNotFoundException(Constant.MESSAGE_EMAIL_EXISTED, Constant.CODE_NOT_FOUND);
        }

        return userMapper.toDto(user.get());
    }

}
