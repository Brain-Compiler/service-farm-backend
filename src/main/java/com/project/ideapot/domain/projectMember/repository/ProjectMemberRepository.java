package com.project.ideapot.domain.projectMember.repository;

import com.project.ideapot.domain.projectMember.domain.ProjectMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectMemberRepository extends JpaRepository<ProjectMember, Long> {
}
