package com.project.ideapot.domain.productMember.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.ideapot.domain.member.domain.Member;
import com.project.ideapot.domain.product.domain.Product;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "member_id")
    private Member member;

    private String role;

}
