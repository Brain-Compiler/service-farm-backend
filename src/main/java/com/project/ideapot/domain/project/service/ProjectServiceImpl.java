package com.project.ideapot.domain.project.service;

import com.project.ideapot.domain.member.repository.MemberRepository;
import com.project.ideapot.domain.project.dto.RecruitTeamMemberRequest;
import com.project.ideapot.domain.project.repository.ProjectRepository;
import com.project.ideapot.domain.projectMember.domain.ProjectMember;
import com.project.ideapot.domain.projectMember.repository.ProjectMemberRepository;
import com.project.ideapot.global.response.BasicResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final MemberRepository memberRepository;

    private final ProjectRepository projectRepository;

    private final ProjectMemberRepository projectMemberRepository;

    @Override
    public ResponseEntity<BasicResponse> recruitTeamMember(RecruitTeamMemberRequest request) {


        return null;
    }

}
