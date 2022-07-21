package com.example.demo.service.dto.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.example.demo.constant.Constant;

import lombok.Data;

@Data
public class SignupRequestDto {

    @NotBlank(message = Constant.CODE_REQUIRED)
    @Email(message = Constant.CODE_INVALID_EMAIL)
    @Size(max = 200, message = Constant.CODE_INVALID_SIZE)
    private String email;

    @NotBlank(message = Constant.CODE_REQUIRED)
    @Size(max = 200, message = Constant.CODE_INVALID_SIZE)
    private String name;

    @NotBlank(message = Constant.CODE_REQUIRED)
    @Size(max = 200, message = Constant.CODE_INVALID_SIZE)
    private String github;

    @NotBlank(message = Constant.CODE_REQUIRED)
    @Size(max = 200, message = Constant.CODE_INVALID_SIZE)
    private String password;

}
