package com.korit.moa.moa.service.implement;

import com.korit.moa.moa.common.constant.ResponseMessage;
import com.korit.moa.moa.dto.ResponseDto;
import com.korit.moa.moa.dto.recommendation.request.RequestRecommendationDto;
import com.korit.moa.moa.dto.recommendation.response.GetResponseRecommendationDto;
import com.korit.moa.moa.dto.recommendation.response.ResponseRecommendationDto;
import com.korit.moa.moa.entity.meetingGroup.MeetingGroup;
import com.korit.moa.moa.entity.recommendation.Recommendation;
import com.korit.moa.moa.entity.recommendation.RecommendationsId;
import com.korit.moa.moa.entity.user.User;
import com.korit.moa.moa.repository.MeetingGroupRepository;
import com.korit.moa.moa.repository.RecommendationRepository;
import com.korit.moa.moa.repository.UserRepository;
import com.korit.moa.moa.service.RecommendationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecommendationServiceImplement implements RecommendationService {

    public final RecommendationRepository recommendationRepository;
    private final MeetingGroupRepository meetingGroupRepository;
    private final UserRepository userRepository;

    @Override
    public ResponseDto<ResponseRecommendationDto> createRecommendation(String userId, RequestRecommendationDto dto) {
        ResponseRecommendationDto data = null;
        Long groupId = dto.getGroupId();

        if(groupId == null) {
            return ResponseDto.setFailed(ResponseMessage.VALIDATION_FAIL + "groupId");
        }

        if(userId == null) {
            return ResponseDto.setFailed(ResponseMessage.VALIDATION_FAIL + "userId");
        }

        try{
            MeetingGroup meetingGroup = meetingGroupRepository.findById(groupId)
                    .orElseThrow(() -> new IllegalArgumentException(ResponseMessage.NOT_EXIST_GROUP));

            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new IllegalArgumentException(ResponseMessage.NOT_EXIST_USER));

        RecommendationsId recommendationId = new RecommendationsId (groupId, userId);
            Recommendation recommendation = Recommendation.builder()
                    .id(recommendationId)
                    .meetingGroup(meetingGroup)
                    .user(user)
                    .build();
            recommendationRepository.save(recommendation);

            data = new ResponseRecommendationDto(recommendation);

        } catch (Exception e) {
            e.printStackTrace();
            ResponseDto.setFailed(ResponseMessage.NOT_EXIST_DATA);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    @Override
    public ResponseDto<List<GetResponseRecommendationDto>> getRecommendation() {

        List<GetResponseRecommendationDto> data = null;
        try{
            List<Object[]> recommendation = recommendationRepository.findAllGroup();

               data = recommendation.stream()
                        .map(GetResponseRecommendationDto::new)
                        .collect(Collectors.toList());

        } catch(Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    @Override
    public ResponseDto<Void> deleteRecommendation(String userId, RequestRecommendationDto dto) {
        Long groupId = dto.getGroupId();

        if(groupId == null && groupId.describeConstable().isEmpty()) {
            return ResponseDto.setFailed(ResponseMessage.NO_PERMISSION + "groupId");
        }

        if(userId == null || userId.isEmpty()) {
            return ResponseDto.setFailed(ResponseMessage.NO_PERMISSION + "userId");
        }

        try {
            RecommendationsId recommendationsId = new RecommendationsId(groupId, userId);

            Optional<Recommendation> recommendationOptional = recommendationRepository.findById(recommendationsId);
            if(recommendationOptional.isPresent() &&  recommendationOptional.get().getId().equals(recommendationsId)) {
                recommendationRepository.deleteById(recommendationsId);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, null);
    }


}
