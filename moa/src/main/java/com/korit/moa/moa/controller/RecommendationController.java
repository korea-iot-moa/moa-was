package com.korit.moa.moa.controller;

import com.korit.moa.moa.common.constant.ApiMappingPattern;
import com.korit.moa.moa.dto.ResponseDto;
import com.korit.moa.moa.dto.recommendation.request.RequestRecommendationDto;
import com.korit.moa.moa.dto.recommendation.response.GetResponseRecommendationDto;
import com.korit.moa.moa.dto.recommendation.response.ResponseRecommendationDto;
import com.korit.moa.moa.service.RecommendationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiMappingPattern.RECOMMENDATION)
@RequiredArgsConstructor
public class RecommendationController {

    private final RecommendationService recommendationService;
    private static final String DEL_RECOMMENDATION = "/user-id";

    @PostMapping
        public ResponseEntity<ResponseDto<ResponseRecommendationDto>> createRecommendation(
                @AuthenticationPrincipal String userId,
                @RequestBody RequestRecommendationDto dto) {

        ResponseDto<ResponseRecommendationDto> response = recommendationService.createRecommendation(userId,dto);

        HttpStatus status = response.isResult() ? HttpStatus.OK:HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }

    @GetMapping
    public ResponseEntity<ResponseDto<List<GetResponseRecommendationDto>>> getRecommendation() {
        ResponseDto<List<GetResponseRecommendationDto>> response = recommendationService.getRecommendation();
        HttpStatus status = response.isResult() ? HttpStatus.OK:HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }

    @DeleteMapping(DEL_RECOMMENDATION)
    public ResponseEntity<ResponseDto<Void>>deleteRecommendation(
            @AuthenticationPrincipal String userId,
            @RequestBody RequestRecommendationDto dto
    ) {
        ResponseDto<Void> response = recommendationService.deleteRecommendation(userId, dto);
        HttpStatus status = response.isResult() ? HttpStatus.OK:HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }
}
