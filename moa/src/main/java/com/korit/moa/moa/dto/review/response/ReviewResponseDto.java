package com.korit.moa.moa.dto.review.response;

import com.korit.moa.moa.entity.review.Review;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReviewResponseDto {

    private Long reviewId;

    private Long groupId;

    private String userId;

    private String reviewContent;

    private String reviewImage;

    private String reviewDate;

    public ReviewResponseDto(Review review) {
        this.reviewId = review.getReviewId();
        this.groupId = review.getGroupId();
        this.userId = review.getUserId();
        this.reviewContent = review.getReviewContent();
        this.reviewImage = review.getReviewImage();
        this.reviewDate = review.getReviewDate();
    }
}
