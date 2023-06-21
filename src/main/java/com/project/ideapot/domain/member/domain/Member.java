package com.project.ideapot.domain.member.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.ideapot.domain.image.domain.Image;
import com.project.ideapot.domain.productMember.domain.ProductMember;
import com.project.ideapot.domain.projectMember.domain.ProjectMember;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 닉네임
    @Column(nullable = false)
    private String username;

    @JsonIgnore
    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    // @Column(nullable = false)
    private int birth;

    private String phone;

    // @Column(nullable = false)
    private String email;

    // 유형(미정, 개인, 단체)
    // @Column(nullable = false)
    private String type;

    // 개인: 직업 입력 // 단체(기관): 학교 또는 회사 입력
    // @Column(nullable = false)
    private String detail;

    @OneToOne
    private Image profile;

    @OneToMany(mappedBy = "member")
    private List<ProductMember> products;

    @OneToMany(mappedBy = "member")
    private List<ProjectMember> projects;

    @Column(nullable = false)
    private String role;

    // 인증 여부
    @Column(nullable = false)
    private Boolean identityVerify;

    @Column(nullable = false)
    private LocalDateTime jointAt;

}
