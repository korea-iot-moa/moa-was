package com.korit.moa.moa.controller;

import com.korit.moa.moa.common.constant.ApiMappingPattern;
import com.korit.moa.moa.dto.ResponseDto;
import com.korit.moa.moa.dto.vote.response.VoteResponseDto;
import com.korit.moa.moa.service.VoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiMappingPattern.VOTE)
@RequiredArgsConstructor
public class VoteController {

    private final VoteService voteService;

    private static final String GET_VOTE = "/{groupId}";

    // 내 그룹 투표 조회
    @GetMapping(GET_VOTE)
    public ResponseEntity<ResponseDto<VoteResponseDto>> getMyGroupVote(
            @PathVariable Long groupId
    ) {
        ResponseDto<VoteResponseDto> response = voteService.getMyGroupVote(groupId);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }

}
