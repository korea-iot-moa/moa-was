package com.korit.moa.moa.service;

import com.korit.moa.moa.dto.ResponseDto;
import com.korit.moa.moa.dto.review.request.CreateRequestDto;
import com.korit.moa.moa.dto.review.request.UpdateRequestDto;
import com.korit.moa.moa.dto.review.response.ReviewResponseDto;

import java.util.List;

public interface ReviewService {
    // 후기 생성
    ResponseDto<ReviewResponseDto> createReview (String userId, CreateRequestDto dto);

    // 후기 전체 조회
    ResponseDto<List<ReviewResponseDto>> getAllReviews();

    // 후기 단건 조회
    ResponseDto<ReviewResponseDto> getReviewById(Long reviewId);

    // 내 후기 조회
    ResponseDto<List<ReviewResponseDto>> getMyReviews(String userId);

    // 후기 수정
    ResponseDto<ReviewResponseDto> updateReview(Long reviewId, String userId, UpdateRequestDto dto);

    // 후기 삭제
    ResponseDto<Void> deleteReview(Long id, String userId);
}
