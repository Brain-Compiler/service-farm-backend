package com.project.ideapot.domain.error.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    INVALID_INPUT_VALUE(400, "INVALID INPUT VALUE");

    private final int status;

    private final String message;

}
