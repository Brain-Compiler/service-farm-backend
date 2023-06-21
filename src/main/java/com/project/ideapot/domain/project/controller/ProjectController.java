package com.project.ideapot.domain.project.controller;

import com.project.ideapot.domain.project.dto.RecruitTeamMemberRequest;
import com.project.ideapot.domain.project.service.ProjectService;
import com.project.ideapot.global.response.BasicResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/project")
public class ProjectController {

    private final ProjectService projectService;

    @PostMapping("/recruit")
    public ResponseEntity<BasicResponse> recruitTeamMember(@RequestPart @Valid RecruitTeamMemberRequest request,
                                                           @RequestPart(name = "teamLogo", required = false) MultipartFile teamLogo,
                                                           @RequestPart(name = "teamPromotion", required = false) MultipartFile teamPromotion,
                                                           @RequestPart(name = "projectPrototype", required = false) List<MultipartFile> projectPrototype) {
        
        return projectService.recruitTeamMember(request);
    }

}
