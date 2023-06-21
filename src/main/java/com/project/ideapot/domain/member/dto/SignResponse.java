package com.project.ideapot.domain.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignResponse {

    private String username;

    private String name;

    private String role;

    private String token;

}
