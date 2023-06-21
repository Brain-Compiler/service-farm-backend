package com.project.ideapot.domain.member.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class RegisterRequest {

    @NotBlank(message = "이름을 작성해주세요.")
    private String name;

    @NotBlank(message = "아이디을 작성해주세요.")
    private String username;

    @NotBlank(message = "비밀번호을 작성해주세요.")
    private String password;

    @NotBlank(message = "비밀번호 확인을 작성해주세요.")
    private String passwordCheck;
}
