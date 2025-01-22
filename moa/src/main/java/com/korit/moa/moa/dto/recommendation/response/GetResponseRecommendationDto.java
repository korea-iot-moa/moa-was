package com.korit.moa.moa.dto.recommendation.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetResponseRecommendationDto {

    private Long groupId;

    public GetResponseRecommendationDto(Object[] objects) {
        this.groupId = objects[0] != null ? ((Number) objects[0]).longValue() : null;
    }
}
