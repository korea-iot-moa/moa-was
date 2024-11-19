package com.korit.moa.moa.service.implement;

import com.korit.moa.moa.common.constant.ResponseMessage;
import com.korit.moa.moa.dto.ResponseDto;
import com.korit.moa.moa.dto.review.request.CreateRequestDto;
import com.korit.moa.moa.dto.review.request.UpdateRequestDto;
import com.korit.moa.moa.dto.review.response.ReviewResponseDto;
import com.korit.moa.moa.entity.review.Review;
import com.korit.moa.moa.repository.ReviewRepository;
import com.korit.moa.moa.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewServiceImplement implements ReviewService {

    private final ReviewRepository reviewRepository;

    // 리뷰 등록
    @Override
    public ResponseDto<ReviewResponseDto> createReview(String userId, CreateRequestDto dto) {
        ReviewResponseDto data = null;
        Long groupId = dto.getGroupId();
        String reviewContent = dto.getReviewContent();
        String reviewImage = dto.getReviewImage();
        LocalDate reviewDate = LocalDate.now();

        try{
            Review review = Review.builder()
                    .groupId(groupId)
                    .userId(userId)
                    .reviewContent(reviewContent)
                    .reviewImage(reviewImage)
                    .reviewDate(String.valueOf(reviewDate))
                    .build();
            reviewRepository.save(review);

            data = new ReviewResponseDto(review);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    // 리뷰 전체 조회
    @Override
    public ResponseDto<List<ReviewResponseDto>> getAllReviews() {
        List<ReviewResponseDto> data = null;

        try{
            List<Review> reviews = reviewRepository.findAll();

            data = reviews.stream()
                    .map(ReviewResponseDto :: new)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    // 리뷰 단건 조회
    @Override
    public ResponseDto<ReviewResponseDto> getReviewById(Long reviewId) {
        ReviewResponseDto data = null;

        try{
            Optional<Review> optionalReview = reviewRepository.findById(reviewId);
            if(optionalReview.isEmpty()) {
                return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_DATA);
            }

            Review review = optionalReview.get();
            data = new ReviewResponseDto(review);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    // 내가 쓴 리뷰 조회
    @Override
    public ResponseDto<List<ReviewResponseDto>> getMyReviews(String userId) {
        List<ReviewResponseDto> data = null;

        try{
            List<Review> reviews  = reviewRepository.findAllByUserId(userId);
            data = reviews.stream()
                    .map(ReviewResponseDto :: new)
                    .collect(Collectors.toList());

            if(data == null || data.isEmpty()) {
                return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_DATA);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    // 내가 쓴 리뷰 수정
    @Override
    public ResponseDto<ReviewResponseDto> updateReview(Long reviewId, String userId, UpdateRequestDto dto) {
        ReviewResponseDto data = null;

        try{
            Optional<Review> optionalReview = reviewRepository.findById(reviewId);
            if(optionalReview.isEmpty()) {
                return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_DATA);
            }

            Review review = optionalReview.get();

            if(!review.getUserId().equals(userId)) {
                return ResponseDto.setFailed(ResponseMessage.NO_PERMISSION);
            }

            review = Review.builder()
                    .reviewId(review.getReviewId())
                    .groupId(review.getReviewId())
                    .userId(review.getUserId())
                    .reviewContent(dto.getReviewContent())
                    .reviewImage(dto.getReviewImage())
                    .reviewDate(review.getReviewDate())
                    .build();

            reviewRepository.save(review);
            data = new ReviewResponseDto(review);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    // 내가 쓴 리뷰 삭제
    @Override
    public ResponseDto<Void> deleteReview(Long reviewId, String userId) {
        try{
            Optional<Review> optionalReview = reviewRepository.findById(reviewId);
            if(optionalReview.isEmpty()) {
                return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_DATA);
            }

            Review deleteReview = optionalReview.get();

            if(!deleteReview.getUserId().equals(userId)) {
                return ResponseDto.setFailed(ResponseMessage.NO_PERMISSION);
            }

            reviewRepository.deleteById(reviewId);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        };
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, null);
    }
}
