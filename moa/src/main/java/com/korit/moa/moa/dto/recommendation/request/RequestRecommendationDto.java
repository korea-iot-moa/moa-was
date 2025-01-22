package com.korit.moa.moa.dto.recommendation.request;

import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestRecommendationDto {

    @NotNull
    private Long groupId;
}
