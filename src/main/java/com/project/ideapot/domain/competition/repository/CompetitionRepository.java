package com.project.ideapot.domain.competition.repository;

import com.project.ideapot.domain.competition.domain.Competition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompetitionRepository extends JpaRepository<Competition, Long> {
}
