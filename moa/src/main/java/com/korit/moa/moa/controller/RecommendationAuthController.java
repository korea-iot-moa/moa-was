package com.korit.moa.moa.controller;

import com.korit.moa.moa.common.constant.ApiMappingPattern;
import com.korit.moa.moa.dto.ResponseDto;
import com.korit.moa.moa.dto.recommendation.response.GetResponseRecommendationDto;
import com.korit.moa.moa.service.RecommendationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(ApiMappingPattern.AUTH)
@RequiredArgsConstructor
public class RecommendationAuthController {

    private final RecommendationService recommendationService;

    @GetMapping
    public ResponseEntity<ResponseDto<List<GetResponseRecommendationDto>>> getAllRecommendationGroup() {
        ResponseDto<List<GetResponseRecommendationDto>> response = recommendationService.getAllRecommendationGroup();
        HttpStatus status = response.isResult() ? HttpStatus.OK:HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }
}
