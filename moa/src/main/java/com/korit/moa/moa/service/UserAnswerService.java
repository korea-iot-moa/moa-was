package com.korit.moa.moa.service;

import com.korit.moa.moa.dto.ResponseDto;
import com.korit.moa.moa.dto.user_answer.request.RequestDeleteUserAnswerDto;
import com.korit.moa.moa.dto.user_answer.request.RequestUserAnswerDto;
import com.korit.moa.moa.dto.user_answer.request.UserAnswerRequestDto;
import com.korit.moa.moa.dto.user_answer.response.ParticipationStatusResponseDto;
import com.korit.moa.moa.dto.user_answer.response.ResponseUserAnswerDto;
import com.korit.moa.moa.dto.user_answer.response.UserAnswerGetReponseDto;

import java.util.List;

public interface UserAnswerService {
    ResponseDto<List<UserAnswerGetReponseDto>> getUserAnswer(Long groupId);
    ResponseDto<Void> approveUserAnswer(Long groupId, RequestDeleteUserAnswerDto dto);
    ResponseDto<Boolean> refuseRequestUserAnswer (Long groupId, RequestDeleteUserAnswerDto dto);
    // 모임참여 답변
    ResponseDto<ResponseUserAnswerDto> createUserAnswer(String userId, UserAnswerRequestDto dto, Long answerId);

    // 모임참여신청 중복 확인
    ResponseDto<Boolean> duplicateUserAnswer(String userId, Long groupId);

    ResponseDto<List<ParticipationStatusResponseDto >> findParticipationStatus(String userId);

    ResponseDto<Boolean> deleteAnswer(Long answerId);
}