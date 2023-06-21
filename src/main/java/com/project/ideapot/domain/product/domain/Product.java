package com.project.ideapot.domain.product.domain;

import com.project.ideapot.domain.category.domain.Category;
import com.project.ideapot.domain.image.domain.Image;
import com.project.ideapot.domain.member.domain.Member;
import com.project.ideapot.domain.productMember.domain.ProductMember;
import com.project.ideapot.domain.techStack.domain.TechStack;
import com.project.ideapot.domain.competition.domain.Competition;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Member publisher;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Boolean teamProjectCheck;

    private String teamName;

    @OneToMany(mappedBy = "product")
    private List<ProductMember> teamMembers;

    @Column(nullable = false)
    private Long cost;

    @OneToOne
    private Image productLogo;

    @OneToOne
    private Image productPromotion;

    @OneToMany
    private List<Image> productImages;

    @Column(nullable = false, columnDefinition = "LONGTEXT")
    private String productExplain;

    @Column(nullable = false, columnDefinition = "LONGTEXT")
    private String productFunctionExplain;

    @OneToMany
    private List<TechStack> techStacks;

    @OneToMany
    private List<Category> categories;

    @Column(nullable = false)
    private Boolean awardCheck;

    @OneToMany
    private List<Competition> competitions;

}
