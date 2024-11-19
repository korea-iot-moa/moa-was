package com.korit.moa.moa.controller;

import com.korit.moa.moa.common.constant.ApiMappingPattern;
import com.korit.moa.moa.dto.ResponseDto;
import com.korit.moa.moa.dto.review.request.CreateRequestDto;
import com.korit.moa.moa.dto.review.request.UpdateRequestDto;
import com.korit.moa.moa.dto.review.response.ReviewResponseDto;
import com.korit.moa.moa.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiMappingPattern.REVIEW)
public class ReviewController {

    private final ReviewService reviewService;

    public static final String GET_REVIEW = "/path/{reviewId}";
    public static final String MY_REVIEWS = "/myReview";
    public static final String PUT_REVIEWS = "/{reviewId}";
    public static final String DEL_REVIEWS = "/{reviewId}";

    // 후기 생성
    @PostMapping
    public ResponseEntity<ResponseDto<ReviewResponseDto>> createReview(
            @AuthenticationPrincipal String userId,
            @RequestBody CreateRequestDto dto
    ) {
        ResponseDto<ReviewResponseDto> response = reviewService.createReview(userId, dto);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }

    // 후기 전체 조회
    @GetMapping
    public ResponseEntity<ResponseDto<List<ReviewResponseDto>>> getAllReviews() {
        ResponseDto<List<ReviewResponseDto>> response = reviewService.getAllReviews();
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }

    // 후기 단건 조회
    @GetMapping(GET_REVIEW)
    public ResponseEntity<ResponseDto<ReviewResponseDto>> getReviewById(
            @PathVariable Long reviewId
    ) {
        ResponseDto<ReviewResponseDto> response = reviewService.getReviewById(reviewId);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }

    // 내 후기 조회
    @GetMapping(MY_REVIEWS)
    public ResponseEntity<ResponseDto<List<ReviewResponseDto>>> getMyReviews(
            @AuthenticationPrincipal String userId
    ) {
        ResponseDto<List<ReviewResponseDto>> response = reviewService.getMyReviews(userId);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }

    // 후기 수정
    @PutMapping(PUT_REVIEWS)
    public ResponseEntity<ResponseDto<ReviewResponseDto>> updateReview(
            @PathVariable Long reviewId,
            @AuthenticationPrincipal String userId,
            @RequestBody UpdateRequestDto dto
            ) {
        ResponseDto<ReviewResponseDto> response = reviewService.updateReview(reviewId, userId, dto);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }

    // 후기 삭제
    @DeleteMapping(DEL_REVIEWS)
    public ResponseEntity<ResponseDto<Void>> deleteReview(
            @PathVariable Long reviewId,
            @AuthenticationPrincipal String userId
    ) {
        ResponseDto<Void> response = reviewService.deleteReview(reviewId, userId);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }
}
