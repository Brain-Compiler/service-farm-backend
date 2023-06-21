package com.project.ideapot.domain.project.service;

import com.project.ideapot.domain.project.dto.RecruitTeamMemberRequest;
import com.project.ideapot.global.response.BasicResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface ProjectService {

    ResponseEntity<BasicResponse> recruitTeamMember(RecruitTeamMemberRequest request);

}
