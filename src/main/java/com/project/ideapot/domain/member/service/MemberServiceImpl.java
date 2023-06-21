package com.project.ideapot.domain.member.service;

import com.project.ideapot.domain.enums.Exception;
import com.project.ideapot.domain.enums.Role;
import com.project.ideapot.domain.enums.Type;
import com.project.ideapot.domain.exception.domain.ApiException;
import com.project.ideapot.domain.member.dto.RegisterRequest;
import com.project.ideapot.domain.member.dto.SignRequest;
import com.project.ideapot.domain.member.dto.SignResponse;
import com.project.ideapot.domain.member.domain.Member;
import com.project.ideapot.domain.member.repository.MemberRepository;
import com.project.ideapot.global.response.BasicResponse;
import com.project.ideapot.global.security.JWTProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    private final PasswordEncoder passwordEncoder;

    private final JWTProvider jwtProvider;

    @Override
    public ResponseEntity<BasicResponse> login(SignRequest request) {
        Member member = memberRepository.findByUsername(request.getUsername()).orElseThrow(() ->
                new BadCredentialsException("잘못된 계정정보입니다."));

        if (!passwordEncoder.matches(request.getPassword(), member.getPassword())) {
            throw new BadCredentialsException("잘못된 계정정보입니다.");
        }

        SignResponse signResponse = SignResponse.builder()
                .username(member.getUsername())
                .name(member.getName())
                .role(member.getRole())
                .token(jwtProvider.createToken(member.getUsername(), member.getRole()))
                .build();

        BasicResponse basicResponse = BasicResponse.builder()
                .message("토큰이 정상적으로 발급되었습니다.")
                .count(1)
                .result(signResponse)
                .build();

        return new ResponseEntity<>(basicResponse, basicResponse.getHttpStatus());
    }

    @Override
    public ResponseEntity<BasicResponse> register(RegisterRequest request) {
        Optional<Member> memberOpt = memberRepository.findByUsername(request.getUsername());

        if (memberOpt.isPresent()) throw new ApiException(Exception.USER_DUPLICATE);

        if (!request.getPassword().equals(request.getPasswordCheck())) throw new ApiException(Exception.PASSWORD_DO_NOT_MATCH);

        Member member = Member.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .name(request.getName())
                .type(Type.UNDEFINED.getKey())
                .role(Role.USER.getKey())
                .identityVerify(false)
                .jointAt(LocalDateTime.now())
                .build();

        memberRepository.save(member);

        BasicResponse basicResponse = BasicResponse.builder()
                .message("사용자가 정상적으로 생성되었습니다.")
                .count(1)
                .result(member)
                .build();

        return new ResponseEntity<>(basicResponse, basicResponse.getHttpStatus());
    }

}