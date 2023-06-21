package com.project.ideapot.domain.product.service;

import com.project.ideapot.domain.product.dto.ProductApplicationRequest;
import com.project.ideapot.global.response.BasicResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public interface ProductService {

    ResponseEntity<BasicResponse> getProduct(Long id);

    ResponseEntity<BasicResponse> getProducts();

    ResponseEntity<BasicResponse> application(ProductApplicationRequest request, MultipartFile productLogo, MultipartFile productPromotion, List<MultipartFile> productImages, List<MultipartFile> awardCertificates) throws IOException;

}
