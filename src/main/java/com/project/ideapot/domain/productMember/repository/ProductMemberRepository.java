package com.project.ideapot.domain.productMember.repository;

import com.project.ideapot.domain.productMember.domain.ProductMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductMemberRepository extends JpaRepository<ProductMember, Long> {
}
