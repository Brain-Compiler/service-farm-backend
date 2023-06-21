package com.project.ideapot.global.security;

import com.project.ideapot.domain.member.domain.Member;
import com.project.ideapot.domain.member.repository.MemberRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class JWTProvider {

    @Value("${jwt.secret.key}")
    private String salt;

    private Key secretKey;

    private Long exp = 1000L * 60 * 60;

    private final MemberRepository memberRepository;

    private final CustomUserDetailsService customUserDetailsService;

    @PostConstruct
    protected void init() {
        secretKey = Keys.hmacShaKeyFor(salt.getBytes(StandardCharsets.UTF_8));
    }

    public String createToken(String username, String role) {
        Claims claims = Jwts.claims()
                .setSubject(username);

        claims.put("role", role);

        Date now = new Date();

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + exp))
                .signWith(secretKey)
                .compact();
    }

    public Authentication getAuthentication(String token) {
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(this.getAccount(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public String getAccount(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public String resolveToken(HttpServletRequest request) {
        return request.getHeader("Authorization");
    }

    public boolean validateToken(String token) {
        try {
            if (!token.substring(0, "Bearer ".length()).equalsIgnoreCase("Bearer ")) {
                return false;
            }

            token = token.split(" ")[1].trim();
            Jws<Claims> claims = Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token);

            log.info(String.valueOf(!claims.getBody().getExpiration().before(new Date())));

            return !claims.getBody().getExpiration().before(new Date());
        } catch (Exception exception) {
            return false;
        }
    }

    public Optional<Member> getMemberByToken(HttpServletRequest httpServletRequest) {
        try {
            String bearerToken = resolveToken(httpServletRequest);

            if (!validateToken(bearerToken)) {
                return Optional.empty();
            }

            String token = bearerToken.split(" ")[1].trim();
            String username = Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();

            log.info("si_number: {}", username);

            return memberRepository.findByUsername(username);
        } catch (Exception exception) {
            log.info(exception.getMessage());

            return Optional.empty();
        }
    }

}
