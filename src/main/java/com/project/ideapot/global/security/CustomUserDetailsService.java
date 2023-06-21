package com.project.ideapot.global.security;

import com.project.ideapot.domain.member.domain.Member;
import com.project.ideapot.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Member> memberOpt = memberRepository.findByUsername(username);

        if (memberOpt.isEmpty()) {
            throw new UsernameNotFoundException("User not found in the database");
        }

        log.info("User found in the database: {}", username);

        return new CustomUserDetails(memberOpt.get());
    }

}
