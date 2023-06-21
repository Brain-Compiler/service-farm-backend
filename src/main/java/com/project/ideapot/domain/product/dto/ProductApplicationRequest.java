package com.project.ideapot.domain.product.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class ProductApplicationRequest {

    @NotBlank(message = "서비스명을 확인해주세요.")
    private String productName;

    private Boolean teamProjectCheck;

    private String teamName;

    private Map<Long, String> teamMembers;

    @PositiveOrZero(message = "가격을 확인해주세요.")
    private Long cost;

    @Size(max = 500)
    @NotBlank(message = "서비스 설명을 확인해주세요.")
    private String productExplain;

    @Size(max = 500)
    @NotBlank(message = "서비스 기능 설명을 확인해주세요.")
    private String productFunctionExplain;

    @NotEmpty(message = "기술 스택을 확인해주세요.")
    private List<Long> techStackIds;

    @NotEmpty(message = "카테고리를 확인해주세요.")
    private List<Long> categoryIds;

    private String serviceUrl;

    private Boolean awardCheck;

    private Map<String, String> competitionMaps;

}
