package com.project.ideapot.domain.member.controller;

import com.project.ideapot.domain.member.dto.RegisterRequest;
import com.project.ideapot.domain.member.dto.SignRequest;
import com.project.ideapot.global.response.BasicResponse;
import com.project.ideapot.domain.member.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/login")
    public ResponseEntity<BasicResponse> login(@RequestBody @Valid SignRequest request) {
        return memberService.login(request);
    }

    @PostMapping("/register")
    public ResponseEntity<BasicResponse> register(@RequestBody @Valid RegisterRequest request) {
        return memberService.register(request);
    }

}