package com.korit.moa.moa.dto.recommendation.response;

import com.korit.moa.moa.entity.recommendation.Recommendation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseRecommendationDto {

    private Long groupId;

    private String userId;

    public ResponseRecommendationDto(Recommendation recommendation) {
        this.groupId = recommendation.getId().getGroupId();
        this.userId = recommendation.getId().getUserId();
    }
}
