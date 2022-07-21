package com.example.demo.service.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {

    private Long id;
    private String name;
    private String password;
    private String email;
    private String picture;

}
