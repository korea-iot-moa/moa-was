package com.korit.moa.moa.service;

import com.korit.moa.moa.dto.ResponseDto;
import com.korit.moa.moa.dto.user_answer.request.RequestDeleteUserAnswerDto;
import com.korit.moa.moa.dto.user_answer.request.RequestUserAnswerDto;
import com.korit.moa.moa.dto.user_answer.response.ResponseUserAnswerDto;

import java.util.List;

public interface UserAnswerService {
    ResponseDto<List<ResponseUserAnswerDto>> getUserAnswer(Long groupId);
    ResponseDto<ResponseUserAnswerDto> postMeetingGroup(Long groupId, RequestUserAnswerDto dto);
    ResponseDto<Void> approveUserAnswer(Long groupId, RequestDeleteUserAnswerDto dto);
    ResponseDto<Void> deleteUserAnswer(Long groupId, RequestDeleteUserAnswerDto dto);
//    모임참여 답변
//    ResponseDto<ResponseUserAnswerDto> createUserAnswer(String userId, RequestUserAnswerDto dto, Long answerId);
}