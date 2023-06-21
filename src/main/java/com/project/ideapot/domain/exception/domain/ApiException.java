package com.project.ideapot.domain.exception.domain;

import com.project.ideapot.domain.enums.Exception;
import lombok.Getter;

@Getter
public class ApiException extends RuntimeException {

    private Exception error;

    public ApiException(Exception e) {
        super(e.getMessage());
        this.error = e;
    }

}