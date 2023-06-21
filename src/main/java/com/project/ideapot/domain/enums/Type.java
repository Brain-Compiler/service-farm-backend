package com.project.ideapot.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Type {

    UNDEFINED("Undefined", "미정"),

    INDIVIDUAL("Individual", "개인"),

    ORGANIZATION("organization", "단체");

    private final String key;

    private final String value;

}
