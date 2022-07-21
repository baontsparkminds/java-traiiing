package com.example.demo.controller.error;

import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.NativeWebRequest;
import org.zalando.problem.Problem;
import org.zalando.problem.ProblemBuilder;
import org.zalando.problem.spring.web.advice.ProblemHandling;
import org.zalando.problem.spring.web.advice.security.SecurityAdviceTrait;
import org.zalando.problem.spring.web.advice.validation.ConstraintViolationProblem;

import com.example.demo.exception.DataExistedException;
import com.example.demo.exception.DataNotFoundException;
import com.example.demo.exception.SigninFailException;
import com.example.demo.service.dto.ErrorResponseDto;
import com.example.demo.service.dto.FieldErrorDto;

@ControllerAdvice
public class CommonControllerAdvice implements ProblemHandling, SecurityAdviceTrait {
    @ExceptionHandler(SigninFailException.class)
    public ResponseEntity<ErrorResponseDto> handleSigninFail(SigninFailException ex) {
        ErrorResponseDto response = ErrorResponseDto.builder().code(ex.getCode()).message(ex.getMessage()).build();
        return new ResponseEntity<ErrorResponseDto>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> dataNotFoundException(DataNotFoundException ex) {
        ErrorResponseDto response = ErrorResponseDto.builder().code(ex.getCode()).message(ex.getMessage()).build();
        return new ResponseEntity<ErrorResponseDto>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DataExistedException.class)
    public ResponseEntity<ErrorResponseDto> dataExistedException(DataExistedException ex) {
        ErrorResponseDto response = ErrorResponseDto.builder().code(ex.getCode()).message(ex.getMessage()).build();
        return new ResponseEntity<ErrorResponseDto>(response, HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<Problem> process(ResponseEntity<Problem> entity, NativeWebRequest request) {
        Problem problem = entity.getBody();

        if (!(problem instanceof ConstraintViolationProblem)) {
            return entity;
        }

        ProblemBuilder builder = Problem.builder();

        if (problem instanceof ConstraintViolationProblem) {
            ConstraintViolationProblem error = (ConstraintViolationProblem) problem;
            builder.with("messageCodes",
                    error.getViolations().stream().map(FieldErrorDto::map).collect(Collectors.toList()));
        }

        return new ResponseEntity<>(builder.build(), entity.getHeaders(), entity.getStatusCode());
    }
}
