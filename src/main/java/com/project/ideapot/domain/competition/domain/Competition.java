package com.project.ideapot.domain.competition.domain;

import com.project.ideapot.domain.image.domain.Image;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Competition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String competitionName;

    private String awardName;

    @OneToOne
    private Image image;

}
