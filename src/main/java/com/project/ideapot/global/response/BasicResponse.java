package com.project.ideapot.global.response;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BasicResponse {

    private Integer code;

    private HttpStatus httpStatus;

    private String message;

    private Integer count;

    private Object result;

    @Builder
    public BasicResponse(String message, Integer count, Object result) {
        this.code = HttpStatus.OK.value();
        this.httpStatus = HttpStatus.OK;
        this.message = message;
        this.count = count;
        this.result = result;
    }

}
