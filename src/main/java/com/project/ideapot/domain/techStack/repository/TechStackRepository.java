package com.project.ideapot.domain.techStack.repository;

import com.project.ideapot.domain.techStack.domain.TechStack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TechStackRepository extends JpaRepository<TechStack, Long> {
}
