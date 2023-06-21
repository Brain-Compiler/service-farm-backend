package com.project.ideapot.global.response;


import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ApiExceptionResponse {

    private String errorCode;

    private String errorMessage;

    @Builder
    public ApiExceptionResponse(String errorCode, String errorMessage){
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

}