package com.project.ideapot.domain.project.domain;

import com.project.ideapot.domain.category.domain.Category;
import com.project.ideapot.domain.image.domain.Image;
import com.project.ideapot.domain.projectMember.domain.ProjectMember;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;

@Entity
@Getter
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String teamName;

    @Column(nullable = false)
    private String teamIntroduce;

    @Column(nullable = false)
    private String teamGoal;

    @Column(nullable = false)
    private Boolean teamExistCheck;

    @OneToMany(mappedBy = "project")
    private List<ProjectMember> teamMembers;

    @Column(nullable = false)
    private Integer pm;

    @Column(nullable = false)
    private Integer designer;

    @Column(nullable = false)
    private Integer frontend;

    @Column(nullable = false)
    private Integer backend;

    @Column(nullable = false)
    private Integer infra;

    @Column(nullable = false)
    private String preferential;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String introduce;

    @Column(nullable = false)
    private String functionExplain;

    @OneToMany
    private List<Category> categories;

    @OneToOne
    private Image teamLogo;

    @OneToOne
    private Image teamPromotion;

    @OneToMany
    private List<Image> projectPrototype;

}
