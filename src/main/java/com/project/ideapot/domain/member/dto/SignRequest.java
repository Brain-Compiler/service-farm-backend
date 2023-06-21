package com.project.ideapot.domain.member.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SignRequest {
    
    @NotBlank(message = "아이디를 작성해주세요.")
    private String username;

    @NotBlank(message = "비밀번호를 작성해주세요.")
    private String password;

}
