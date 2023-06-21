package com.project.ideapot.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ImageType {

    PROFILE("Profile", "프로필"),

    COMPETITION("Competition", "대회"),

    PROJECT("Project", "프로젝트"),

    PROJECT_LOGO("Project Logo", "프로젝트 로고"),

    PROJECT_PROMOTION("Project Promotion", "프로젝트 홍보");

    private final String key;

    private final String name;

}
