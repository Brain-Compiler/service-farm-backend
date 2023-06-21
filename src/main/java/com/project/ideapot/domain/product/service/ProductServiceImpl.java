package com.project.ideapot.domain.product.service;

import com.project.ideapot.domain.enums.Exception;
import com.project.ideapot.domain.enums.ImageType;
import com.project.ideapot.domain.exception.domain.ApiException;
import com.project.ideapot.domain.image.service.ImageService;
import com.project.ideapot.domain.product.domain.Product;
import com.project.ideapot.domain.product.dto.ProductApplicationRequest;
import com.project.ideapot.domain.category.domain.Category;
import com.project.ideapot.domain.category.repository.CategoryRepository;
import com.project.ideapot.domain.member.domain.Member;
import com.project.ideapot.domain.member.repository.MemberRepository;
import com.project.ideapot.domain.product.repository.ProductRepository;
import com.project.ideapot.domain.productMember.domain.ProductMember;
import com.project.ideapot.domain.productMember.repository.ProductMemberRepository;
import com.project.ideapot.domain.techStack.domain.TechStack;
import com.project.ideapot.domain.techStack.repository.TechStackRepository;
import com.project.ideapot.global.response.BasicResponse;
import com.project.ideapot.domain.competition.service.CompetitionService;
import com.project.ideapot.global.security.JWTProvider;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final HttpServletRequest httpServletRequest;

    private final MemberRepository memberRepository;

    private final ProductRepository productRepository;

    private final TechStackRepository techStackRepository;

    private final CategoryRepository categoryRepository;

    private final ProductMemberRepository productMemberRepository;

    private final ImageService imageService;

    private final CompetitionService competitionService;

    private final JWTProvider jwtProvider;

    @Override
    public ResponseEntity<BasicResponse> getProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ApiException(Exception.PRODUCT_NOT_FOUND));

        BasicResponse basicResponse = BasicResponse.builder()
                .message("프로젝트를 정상적으로 찾았습니다.")
                .count(1)
                .result(product)
                .build();

        return new ResponseEntity<>(basicResponse, basicResponse.getHttpStatus());
    }

    @Override
    public ResponseEntity<BasicResponse> getProducts() {
        List<Product> products = productRepository.findAll();

        if (products.isEmpty()) throw new ApiException(Exception.PRODUCT_NO_CONTENT);

        BasicResponse basicResponse = BasicResponse.builder()
                .message("프로젝트를 정상적으로 찾았습니다.")
                .count(products.size())
                .result(products)
                .build();

        return new ResponseEntity<>(basicResponse, basicResponse.getHttpStatus());
    }

    @Override
    @Transactional
    public ResponseEntity<BasicResponse> application(ProductApplicationRequest request,
                                                     MultipartFile productLogo,
                                                     MultipartFile productPromotion,
                                                     List<MultipartFile> productImages,
                                                     List<MultipartFile> awardCertificates) throws IOException {
        Member publisher = jwtProvider.getMemberByToken(httpServletRequest)
                .orElseThrow(() -> new ApiException(Exception.USER_NOT_FOUND));

        if (productLogo.isEmpty()) throw new ApiException(Exception.PRODUCT_LOGO_NOT_FOUND);

        if (productPromotion.isEmpty()) throw new ApiException(Exception.PRODUCT_PROMOTION_NOT_FOUND);

        if (productImages.size() < 1) throw new ApiException(Exception.PRODUCT_IMAGE_NOT_FOUND); // 4개로 고치기

        Product product = new Product();
        List<ProductMember> members = new ArrayList<>();

        if (request.getTeamProjectCheck()) {
            if (request.getTeamName().equals("")) throw new ApiException(Exception.TEAM_NAME_NOT_FOUND);

            if (request.getTeamMembers().isEmpty()) throw new ApiException(Exception.TEAM_MEMBER_NOT_FOUND);

            request.getTeamMembers().forEach(
                    (memberId, role) ->
                        members.add(ProductMember.builder()
                                        .product(product)
                                        .member(memberRepository.findById(memberId).orElseThrow(
                                                () -> new ApiException(Exception.USER_NOT_FOUND))
                                        )
                                        .role(role)
                                        .build())
            );

            product.setTeamMembers(members);
            product.setTeamName(request.getTeamName());
        }

        List<TechStack> techStacks = new ArrayList<>();

        for (Long techStackId : request.getTechStackIds()) {
            TechStack techStack = techStackRepository.findById(techStackId)
                    .orElseThrow(() -> new ApiException(Exception.TEACH_STACK_NOT_FOUND));

            techStacks.add(techStack);
        }

        List<Category> categories = new ArrayList<>();

        for (Long categoryId : request.getCategoryIds()) {
            Category category = categoryRepository.findById(categoryId)
                    .orElseThrow(() -> new ApiException(Exception.CATEGORY_NOT_FOUND));

            categories.add(category);
        }

        product.setPublisher(publisher);
        product.setName(request.getProductName());
        product.setTeamProjectCheck(request.getTeamProjectCheck());
        product.setCost(request.getCost());
        product.setProductLogo(imageService.saveImages(List.of(productLogo), ImageType.PROJECT_LOGO.getKey()).get(0));
        product.setProductPromotion(imageService.saveImages(List.of(productPromotion), ImageType.PROJECT_PROMOTION.getKey()).get(0));
        product.setProductImages(imageService.saveImages(productImages, ImageType.PROJECT.getKey()));
        product.setProductExplain(request.getProductExplain());
        product.setProductFunctionExplain(request.getProductFunctionExplain());
        product.setTechStacks(techStacks);
        product.setCategories(categories);
        product.setAwardCheck(request.getAwardCheck());

        if (request.getAwardCheck()) {
            product.setCompetitions(competitionService.addCompetition(request.getCompetitionMaps(), awardCertificates));
        }

        if (members.size() > 0) {
            productMemberRepository.saveAll(members);
        }

        productRepository.save(product);

        BasicResponse basicResponse = BasicResponse.builder()
                .message("프로젝트가 정상적을 등록되었습니다.")
                .count(1)
                .result(product)
                .build();

        return new ResponseEntity<>(basicResponse, basicResponse.getHttpStatus());
    }

}

