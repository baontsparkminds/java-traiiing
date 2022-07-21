package com.example.demo.service.mapper;

import org.springframework.stereotype.Component;

import com.example.demo.entity.User;
import com.example.demo.service.dto.UserDto;
import com.example.demo.service.dto.request.SignupRequestDto;

@Component
public class UserMapper {
    public UserDto toDto(User user) {
        return UserDto
                .builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .picture(user.getPicture())
                .build();
    }

    public User toEntity(UserDto userDto) {

        User user = new User();

        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setPicture(userDto.getPicture());

        return user;
    }

    public User toEntity(SignupRequestDto signupRequestDto) {

        User user = new User();

        user.setName(signupRequestDto.getName());
        user.setEmail(signupRequestDto.getEmail());
        user.setPassword(signupRequestDto.getPassword());

        return user;
    }

}
