package com.project.ideapot.domain.projectMember.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.ideapot.domain.member.domain.Member;
import com.project.ideapot.domain.project.domain.Project;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "project_id")
    private Project project;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "member_id")
    private Member member;

    private String role;

}
