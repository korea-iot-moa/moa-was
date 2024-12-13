package com.korit.moa.moa.controller;

import com.korit.moa.moa.common.constant.ApiMappingPattern;
import com.korit.moa.moa.dto.ResponseDto;
import com.korit.moa.moa.dto.user_answer.request.RequestUserAnswerDto;
import com.korit.moa.moa.dto.user_answer.response.ResponseUserAnswerDto;
import com.korit.moa.moa.service.UserAnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiMappingPattern.USER_ANSWER)
@RequiredArgsConstructor
public class UserAnswerController {

    private final UserAnswerService userAnswerService;
    @PostMapping
    public ResponseEntity<ResponseDto<ResponseUserAnswerDto>> createUserAnswer(
            @AuthenticationPrincipal String userId,
            @RequestBody RequestUserAnswerDto dto,
            Long answerId
            ){
        ResponseDto<ResponseUserAnswerDto> response = userAnswerService.createUserAnswer(userId, dto, answerId);
        HttpStatus status = response.isResult() ? HttpStatus.OK:HttpStatus.BAD_REQUEST;
        return  ResponseEntity.status(status).body(response);
    }

}
