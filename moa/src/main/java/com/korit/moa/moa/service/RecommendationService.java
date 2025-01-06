package com.korit.moa.moa.service;

import com.korit.moa.moa.dto.ResponseDto;
import com.korit.moa.moa.dto.recommendation.request.RequestRecommendationDto;
import com.korit.moa.moa.dto.recommendation.response.GetResponseRecommendationDto;
import com.korit.moa.moa.dto.recommendation.response.ResponseRecommendationDto;
import com.korit.moa.moa.entity.meetingGroup.GroupCategory;
import com.korit.moa.moa.entity.meetingGroup.GroupTypeCategory;
import com.korit.moa.moa.entity.recommendation.Recommendation;

import java.util.List;

public interface RecommendationService {
    ResponseDto<ResponseRecommendationDto> createRecommendation(String userId, RequestRecommendationDto dto);

    ResponseDto<Void> deleteRecommendation(String userId, RequestRecommendationDto dto);

    ResponseDto<List<GetResponseRecommendationDto>> getRecommendation(String userId);

    ResponseDto<List<GetResponseRecommendationDto>> getAllRecommendationGroup();
}
