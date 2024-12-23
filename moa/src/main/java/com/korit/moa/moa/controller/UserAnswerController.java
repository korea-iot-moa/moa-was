package com.korit.moa.moa.controller;


import com.korit.moa.moa.common.constant.ApiMappingPattern;
import com.korit.moa.moa.dto.ResponseDto;
import com.korit.moa.moa.dto.user_answer.request.RequestDeleteUserAnswerDto;
import com.korit.moa.moa.dto.user_answer.request.RequestUserAnswerDto;
import com.korit.moa.moa.dto.user_answer.response.ResponseGetUserAnswer;
import com.korit.moa.moa.dto.user_answer.response.ResponseUserAnswerDto;
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
    private static final String POST_USER_ANSWER = "approved/{groupId}";

    //모임 참가
    @PostMapping(GET_USER_ANSWER)
    public ResponseEntity<ResponseDto<ResponseUserAnswerDto>> postMeetingGroup(
            @PathVariable Long groupId, @RequestBody RequestUserAnswerDto dto)
    {
        ResponseDto<ResponseUserAnswerDto> response = userAnswerService.postMeetingGroup(groupId, dto);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }

    //참여 요청 조회
    @GetMapping(GET_USER_ANSWER)
    public ResponseEntity<ResponseDto<List<ResponseGetUserAnswer>>> getUserAnswer(@PathVariable Long groupId){
        ResponseDto<List<ResponseGetUserAnswer>> response = userAnswerService.getUserAnswer(groupId);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }

    //참여거절
    @DeleteMapping(GET_USER_ANSWER)
    public ResponseEntity<ResponseDto<Void>> deleteUserAnswer(
            @PathVariable Long groupId, @RequestBody RequestDeleteUserAnswerDto dto)
    {
        ResponseDto<Void> response = userAnswerService.deleteUserAnswer(groupId, dto);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }

    //참여 승인
    @PostMapping(POST_USER_ANSWER)
    public ResponseEntity<ResponseDto<Void>> approveUserAnswer(
            @PathVariable Long groupId, @RequestBody RequestDeleteUserAnswerDto dto)
    {
        ResponseDto<Void> response = userAnswerService.approveUserAnswer(groupId, dto);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }

//    모임참여 신청
//    @PostMapping
//    public ResponseEntity<ResponseDto<ResponseUserAnswerDto>> createUserAnswer(
//            @AuthenticationPrincipal String userId,
//            @RequestBody RequestUserAnswerDto dto,
//            Long answerId
//    ){
//        ResponseDto<ResponseUserAnswerDto> response = userAnswerService.createUserAnswer(userId, dto, answerId);
//        HttpStatus status = response.isResult() ? HttpStatus.OK:HttpStatus.BAD_REQUEST;
//        return  ResponseEntity.status(status).body(response);
//    }

}