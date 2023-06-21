package com.project.ideapot.domain.member.service;

import com.project.ideapot.domain.member.dto.RegisterRequest;
import com.project.ideapot.domain.member.dto.SignRequest;
import com.project.ideapot.global.response.BasicResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface MemberService {

    ResponseEntity<BasicResponse> login(SignRequest request);

    ResponseEntity<BasicResponse> register(RegisterRequest request);

}
