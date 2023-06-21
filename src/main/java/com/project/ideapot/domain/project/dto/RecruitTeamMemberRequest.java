package com.project.ideapot.domain.project.dto;


import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class RecruitTeamMemberRequest {


    private String teamName;

    private String teamIntroduce;

    private Boolean teamExistCheck;

    private Map<Long, String> teamMembers;

    private Integer pm;

    private Integer designer;

    private Integer frontend;

    private Integer backend;

    private Integer infra;

    private String preferential;

    private String name;

    private String introduce;

    private String functionExplain;

    private List<Long> categoryIds;

}
