package com.project.ideapot.domain.competition.service;

import com.project.ideapot.domain.competition.domain.Competition;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public interface CompetitionService {

    List<Competition> addCompetition(Map<String, String> competitionMaps, List<MultipartFile> awardCertificates) throws IOException;

}
