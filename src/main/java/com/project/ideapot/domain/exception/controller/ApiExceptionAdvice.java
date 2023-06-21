package com.project.ideapot.domain.exception.controller;

import com.project.ideapot.domain.enums.Exception;
import com.project.ideapot.domain.exception.domain.ApiException;
import com.project.ideapot.global.response.ApiExceptionResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;

@RestControllerAdvice
public class ApiExceptionAdvice {

    @ExceptionHandler({ApiException.class})
    public ResponseEntity<ApiExceptionResponse> exceptionHandler(final ApiException e) {
        return ResponseEntity
                .status(e.getError().getStatus())
                .body(ApiExceptionResponse.builder()
                        .errorCode(e.getError().getCode())
                        .errorMessage(e.getError().getMessage())
                        .build());
    }

    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<ApiExceptionResponse> exceptionHandler(final RuntimeException e) {
        e.printStackTrace();
        return ResponseEntity
                .status(Exception.RUNTIME_EXCEPTION.getStatus())
                .body(ApiExceptionResponse.builder()
                        .errorCode(Exception.RUNTIME_EXCEPTION.getCode())
                        .errorMessage(e.getMessage())
                        .build());
    }

    @ExceptionHandler({AccessDeniedException.class})
    public ResponseEntity<ApiExceptionResponse> exceptionHandler(final AccessDeniedException e) {
        e.printStackTrace();
        return ResponseEntity
                .status(Exception.ACCESS_DENIED_EXCEPTION.getStatus())
                .body(ApiExceptionResponse.builder()
                        .errorCode(Exception.ACCESS_DENIED_EXCEPTION.getCode())
                        .errorMessage(e.getMessage())
                        .build());
    }

    @ExceptionHandler({java.lang.Exception.class})
    public ResponseEntity<ApiExceptionResponse> exceptionHandler(final java.lang.Exception e) {
        e.printStackTrace();
        return ResponseEntity
                .status(Exception.INTERNAL_SERVER_ERROR.getStatus())
                .body(ApiExceptionResponse.builder()
                        .errorCode(Exception.INTERNAL_SERVER_ERROR.getCode())
                        .errorMessage(e.getMessage())
                        .build());
    }

}