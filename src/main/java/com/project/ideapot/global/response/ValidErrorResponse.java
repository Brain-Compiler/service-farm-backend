package com.project.ideapot.global.response;

import com.project.ideapot.domain.error.domain.FieldError;
import com.project.ideapot.domain.error.enums.ErrorCode;
import lombok.Getter;
import org.springframework.validation.BindingResult;

import java.util.List;

@Getter
public class ValidErrorResponse {

    private String message;

    private int status;

    private List<FieldError> errors;

    private ValidErrorResponse(final ErrorCode code, final List<FieldError> errors) {
        this.message = code.getMessage();
        this.status = code.getStatus();
        this.errors = errors;
    }

    public static ValidErrorResponse of(final ErrorCode code, final BindingResult bindingResult) {
        return new ValidErrorResponse(code, FieldError.of(bindingResult));
    }

}
