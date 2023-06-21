package com.project.ideapot.domain.enums;


import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Getter
@ToString
public enum Exception {

    RUNTIME_EXCEPTION(HttpStatus.BAD_REQUEST, "E0001"),
    ACCESS_DENIED_EXCEPTION(HttpStatus.UNAUTHORIZED, "E0002"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "E0003"),

    // USER
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "U0001", "사용자를 찾을 수 없습니다."),
    USER_DUPLICATE(HttpStatus.CONFLICT, "U0002", "중복되는 아이디 입니다."),
    PASSWORD_DO_NOT_MATCH(HttpStatus.CONFLICT, "U0003", "비밀번호가 일치하지 않습니다."),

    // PRODUCT
    PRODUCT_NOT_FOUND(HttpStatus.NOT_FOUND, "P0001", "프로젝트를 찾을 수 없습니다."),
    PRODUCT_NO_CONTENT(HttpStatus.NO_CONTENT, "P0002", "프로젝트가 존재하지 않습니다."),
    PRODUCT_LOGO_NOT_FOUND(HttpStatus.NOT_FOUND, "P0003", "서비스 로고를 확인해주세요."),
    PRODUCT_PROMOTION_NOT_FOUND(HttpStatus.NOT_FOUND, "P0004", "서비스 홍보 이미지를 확인해주세요."),
    PRODUCT_IMAGE_NOT_FOUND(HttpStatus.NOT_FOUND, "P0005", "서비스 이미지를 확인해주세요."),

    // TEAM
    TEAM_NAME_NOT_FOUND(HttpStatus.NOT_FOUND, "T0001", "팀명을 확인해주세요."),
    TEAM_MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "T0002", "팀원을 확인해주세요."),

    // TEACH STACK
    TEACH_STACK_NOT_FOUND(HttpStatus.NOT_FOUND, "TS0001", "기술 스택을 찾을 수 없습니다."),

    // CATEGORY
    CATEGORY_NOT_FOUND(HttpStatus.NOT_FOUND, "C0001", "카테고리를 찾을 수 없습니다.");

    private final HttpStatus httpStatus;

    private final String code;

    private String message;

    Exception(HttpStatus httpStatus, String code) {
        this.httpStatus = httpStatus;
        this.code = code;
    }

    Exception(HttpStatus httpStatus, String code, String message) {
        this.httpStatus = httpStatus;
        this.code = code;
        this.message = message;
    }

}