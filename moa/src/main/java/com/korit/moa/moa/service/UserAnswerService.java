package com.korit.moa.moa.service;

import com.korit.moa.moa.dto.ResponseDto;
import com.korit.moa.moa.dto.user_answer.request.RequestDeleteUserAnswerDto;
import com.korit.moa.moa.dto.user_answer.request.RequestUserAnswerDto;
import com.korit.moa.moa.dto.user_answer.request.UserAnswerRequestDto;
import com.korit.moa.moa.dto.user_answer.response.ParticipationStatusResponseDto;
import com.korit.moa.moa.dto.user_answer.response.ResponseUserAnswerDto;
import com.korit.moa.moa.dto.user_answer.response.UserAnswerGetResponseDto;

import java.util.List;

public interface UserAnswerService {

    ResponseDto<List<UserAnswerGetResponseDto>> getUserAnswer(Long groupId);

    ResponseDto<ResponseUserAnswerDto> postMeetingGroup(Long groupId, RequestUserAnswerDto dto);

    ResponseDto<Void> approveUserAnswer(Long groupId, RequestDeleteUserAnswerDto dto);

    ResponseDto<Boolean> refuseRequestUserAnswer (Long groupId, RequestDeleteUserAnswerDto dto);

    ResponseDto<ResponseUserAnswerDto> createUserAnswer(String userId, UserAnswerRequestDto dto, Long answerId);

    ResponseDto<Boolean> duplicateUserAnswer(String userId, Long groupId);

    ResponseDto<List<ParticipationStatusResponseDto >> findParticipationStatus(String userId);

    ResponseDto<Boolean> deleteAnswer(Long answerId);
}