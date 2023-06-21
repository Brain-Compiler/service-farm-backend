package com.project.ideapot.domain.product.controller;

import com.project.ideapot.domain.product.dto.ProductApplicationRequest;
import com.project.ideapot.global.response.BasicResponse;
import com.project.ideapot.domain.product.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/project")
public class ProductController {

    private final ProductService productService;

    @GetMapping("/{id}")
    public ResponseEntity<BasicResponse> getProduct(@PathVariable Long id) {
        return productService.getProduct(id);
    }

    @GetMapping("/list")
    public ResponseEntity<BasicResponse> getProducts() {
        return productService.getProducts();
    }

    @PostMapping("/application")
    public ResponseEntity<BasicResponse> application(@RequestPart(name = "request") @Valid ProductApplicationRequest request,
                                                     @RequestPart(name = "productLogo") MultipartFile productLogo,
                                                     @RequestPart(name = "productPromotion", required = false) MultipartFile productPromotion,
                                                     @RequestPart(name = "productImages", required = false) List<MultipartFile> productImages,
                                                     @RequestPart(name = "awardCertificates", required = false) List<MultipartFile> awardCertificates) throws IOException {
        return productService.application(request, productLogo, productPromotion, productImages, awardCertificates);
    }

}
