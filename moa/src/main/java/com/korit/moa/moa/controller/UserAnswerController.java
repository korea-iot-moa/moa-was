package com.korit.moa.moa.controller;

import com.korit.moa.moa.common.constant.ApiMappingPattern;
import com.korit.moa.moa.dto.ResponseDto;
import com.korit.moa.moa.dto.user_answer.request.RequestDeleteUserAnswerDto;
import com.korit.moa.moa.dto.user_answer.request.RequestUserAnswerDto;
import com.korit.moa.moa.dto.user_answer.request.UserAnswerRequestDto;
import com.korit.moa.moa.dto.user_answer.response.ParticipationStatusResponseDto;
import com.korit.moa.moa.dto.user_answer.response.ResponseUserAnswerDto;
import com.korit.moa.moa.dto.user_answer.response.UserAnswerGetResponseDto;
import com.korit.moa.moa.service.UserAnswerService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiMappingPattern.USER_ANSWER)
public class UserAnswerController {

    private final UserAnswerService userAnswerService;

    private static final String GET_USER_ANSWER = "/{groupId}";
    private static final String PUT_REFUSE_REQUEST = "/{groupId}";
    private static final String DELETE_USER_ANSWER = "/{answerId}";
    private static final String PUT_APPROVE_REQUEST = "approved/{groupId}";
    private static final String GET_USER_ANSWER_DUPLICATION = "/duplication/{groupId}";
    private static final String GROUP_PARTICIPATION_STATUS = "/participation-status";

    @GetMapping(GET_USER_ANSWER)
    public ResponseEntity<ResponseDto<List<UserAnswerGetResponseDto>>> getUserAnswer(@PathVariable Long groupId) {
        ResponseDto<List<UserAnswerGetResponseDto>> response = userAnswerService.getUserAnswer(groupId);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }

    @PutMapping(PUT_REFUSE_REQUEST)
    public ResponseEntity<ResponseDto<Boolean>> refuseRequestUserAnswer(
            @PathVariable Long groupId,
            @RequestBody RequestDeleteUserAnswerDto dto
    ) {
        ResponseDto<Boolean> response = userAnswerService.refuseRequestUserAnswer(groupId, dto);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }

    @PostMapping(PUT_APPROVE_REQUEST)
    public ResponseEntity<ResponseDto<Void>> approveUserAnswer(
            @PathVariable Long groupId,
            @RequestBody RequestDeleteUserAnswerDto dto
    ) {
        ResponseDto<Void> response = userAnswerService.approveUserAnswer(groupId, dto);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }

    @PostMapping
    public ResponseEntity<ResponseDto<ResponseUserAnswerDto>> createUserAnswer(
            @AuthenticationPrincipal String userId,
            @RequestBody UserAnswerRequestDto dto,
            Long answerId
    ) {
        ResponseDto<ResponseUserAnswerDto> response = userAnswerService.createUserAnswer(userId, dto, answerId);
        HttpStatus status = response.isResult() ? HttpStatus.OK:HttpStatus.BAD_REQUEST;
        return  ResponseEntity.status(status).body(response);
    }

    @GetMapping(GET_USER_ANSWER_DUPLICATION)
    public ResponseEntity<ResponseDto<Boolean>> duplicateUserAnswer(
            @AuthenticationPrincipal String userId,
            @PathVariable Long groupId
    ) {
        ResponseDto<Boolean> response = userAnswerService.duplicateUserAnswer(userId, groupId);
        HttpStatus status = response.isResult() ? HttpStatus.OK:HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }

    @GetMapping(GROUP_PARTICIPATION_STATUS)
    public ResponseEntity<ResponseDto<List<ParticipationStatusResponseDto>>> findParticipationStatus(
            @AuthenticationPrincipal String userId
    ) {
        ResponseDto<List<ParticipationStatusResponseDto>> response = userAnswerService.findParticipationStatus(userId);
        HttpStatus status = response.isResult() ? HttpStatus.OK:HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }

    @DeleteMapping(DELETE_USER_ANSWER)
    public ResponseEntity<ResponseDto<Boolean>> deleteUserAnswer (@PathVariable Long answerId) {
        ResponseDto<Boolean> response = userAnswerService.deleteAnswer(answerId);
        HttpStatus status = response.isResult() ? HttpStatus.OK:HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }
}