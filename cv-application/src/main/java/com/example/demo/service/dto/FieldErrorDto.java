package com.example.demo.service.dto;

import org.zalando.problem.spring.web.advice.validation.Violation;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class FieldErrorDto {

    private String field;
    private String code;

    public static FieldErrorDto map(Violation violation) {
        return FieldErrorDto.builder().field(violation.getField()).code(violation.getMessage()).build();
    }
}
