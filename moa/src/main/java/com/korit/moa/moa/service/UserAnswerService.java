package com.korit.moa.moa.service;

import com.korit.moa.moa.dto.ResponseDto;
import com.korit.moa.moa.dto.user_answer.request.RequestUserAnswerDto;
import com.korit.moa.moa.dto.user_answer.response.ResponseUserAnswerDto;

public interface UserAnswerService {
    ResponseDto<ResponseUserAnswerDto> createUserAnswer(String userId, RequestUserAnswerDto dto, Long answerId);
}
