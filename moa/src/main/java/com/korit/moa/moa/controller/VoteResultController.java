package com.korit.moa.moa.controller;

import com.korit.moa.moa.common.constant.ApiMappingPattern;
import com.korit.moa.moa.dto.ResponseDto;
import com.korit.moa.moa.dto.vote_result.request.VoteResultRequestDto;
import com.korit.moa.moa.dto.vote_result.response.VoteResultResponseDto;
import com.korit.moa.moa.service.VoteResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiMappingPattern.VOTE_RESULT)
public class VoteResultController {

    private final VoteResultService voteResultService;
    private static final String GET_VOTE_RESULT = "/{groupId}";

    @PostMapping
    public ResponseEntity<ResponseDto<VoteResultResponseDto>> createVoteResult(
            @AuthenticationPrincipal String userId,
            @RequestBody VoteResultRequestDto dto
    ) {
        ResponseDto<VoteResultResponseDto> response = voteResultService.createVoteResult(userId, dto);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }

    @GetMapping(GET_VOTE_RESULT)
    //투표 결과 조회
    public ResponseEntity<ResponseDto<List<VoteResultResponseDto>>> getVoteResult(@PathVariable Long voteId){
        ResponseDto<List<VoteResultResponseDto>> response = voteResultService.getVoteResult(voteId);
        HttpStatus status  = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return  ResponseEntity.status(status).body(response);
    }

}
