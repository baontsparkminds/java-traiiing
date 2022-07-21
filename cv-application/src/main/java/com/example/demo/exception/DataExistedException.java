package com.example.demo.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class DataExistedException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private final String message;
    private final String code;
}
