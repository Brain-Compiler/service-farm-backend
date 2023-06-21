package com.project.ideapot.domain.competition.service;

import com.project.ideapot.domain.competition.domain.Competition;
import com.project.ideapot.domain.competition.repository.CompetitionRepository;
import com.project.ideapot.domain.enums.ImageType;
import com.project.ideapot.domain.image.domain.Image;
import com.project.ideapot.domain.image.service.ImageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class CompetitionServiceImpl implements CompetitionService {

    private final ImageService imageService;

    private final CompetitionRepository competitionRepository;

    @Override
    public List<Competition> addCompetition(Map<String, String> competitionMaps, List<MultipartFile> awardCertificates) throws IOException {
        List<Competition> competitions = new ArrayList<>();
        Iterator competitionsIter = competitionMaps.entrySet().iterator();
        List<Image> images = imageService.saveImages(awardCertificates, ImageType.COMPETITION.getKey());
        int i = 0;

        log.info(String.valueOf(competitionMaps.size()));

        while (competitionsIter.hasNext()) {
            Map.Entry<String, String> competitonEntry = (Map.Entry) competitionsIter.next();
            String competitionName = competitonEntry.getKey();
            String awardName = competitonEntry.getValue();

            log.info("name1: {}", competitionName);
            log.info("name2: {}", awardName);
            log.info("i: {}", i);

            Competition competition = Competition.builder()
                    .competitionName(competitionName)
                    .awardName(awardName)
                    .image(images.get(i++))
                    .build();

            competitionRepository.save(competition);
            competitions.add(competition);
        }

        return competitions;
    }

}
