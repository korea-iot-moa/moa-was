package com.korit.moa.moa.service;

import com.korit.moa.moa.dto.ResponseDto;
import com.korit.moa.moa.dto.review.request.CreateRequestDto;
import com.korit.moa.moa.dto.review.request.UpdateRequestDto;
import com.korit.moa.moa.dto.review.response.ReviewResponseDto;

import java.util.List;

public interface ReviewService {
    ResponseDto<ReviewResponseDto> createReview (String userId, CreateRequestDto dto);

    ResponseDto<ReviewResponseDto> getReviewById(Long reviewId);

    ResponseDto<List<ReviewResponseDto>> getMyReviews(String userId);

    ResponseDto<ReviewResponseDto> updateReview(Long reviewId, String userId, UpdateRequestDto dto);

    ResponseDto<Void> deleteReview(Long id, String userId);

    ResponseDto<List<ReviewResponseDto>> getAllReviews(int page, int size);

}
