package com.project.ideapot.domain.image.repository;

import com.project.ideapot.domain.image.domain.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
}
