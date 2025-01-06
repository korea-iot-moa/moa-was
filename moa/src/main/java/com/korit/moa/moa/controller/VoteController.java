package com.korit.moa.moa.controller;

import com.korit.moa.moa.common.constant.ApiMappingPattern;
import com.korit.moa.moa.dto.ResponseDto;
import com.korit.moa.moa.dto.vote.request.RequestUpdateVoteDto;
import com.korit.moa.moa.dto.vote.request.RequestVoteDto;
import com.korit.moa.moa.dto.vote.response.PostVoteResponseDto;
import com.korit.moa.moa.dto.vote.response.VoteResponseDto;
import com.korit.moa.moa.service.VoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiMappingPattern.VOTE)
@RequiredArgsConstructor
public class VoteController {

    private final VoteService voteService;

    private static final String GET_VOTE = "/{groupId}";
    private static final String PUT_VOTE = "/{voteId}";
    private static final String POST_VOTE = "/{voteId}";


    // 내 그룹 투표 조회
    @GetMapping(GET_VOTE)
    public ResponseEntity<ResponseDto<VoteResponseDto>> getMyGroupVote(
            @PathVariable Long groupId
    ) {

        ResponseDto<VoteResponseDto> response = voteService.getMyGroupVote(groupId);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }
    //투표 등록
    @PostMapping
    public ResponseEntity<ResponseDto<PostVoteResponseDto>> postMyGroupVote(
            @RequestBody RequestVoteDto dto
    ){
        System.out.println(dto.getVoteContent());
        System.out.println(dto.getCreatorId());
        ResponseDto<PostVoteResponseDto> response = voteService.postMyGroupVote(dto);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }

    //투표 수정
    @PutMapping(PUT_VOTE)
    public ResponseEntity<ResponseDto<VoteResponseDto>> updateMyGroupVote(
            @PathVariable Long voteId, @RequestBody RequestUpdateVoteDto dto
    ){
        ResponseDto<VoteResponseDto> response = voteService.updateMyGroupVote(voteId,dto);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }

    //투표 조회 삭제
    @DeleteMapping(PUT_VOTE)
    public ResponseEntity<ResponseDto<Void>> deleteMyGroupVote(
            @PathVariable Long voteId
    ){
        ResponseDto<Void> response = voteService.deleteMyGroupVote(voteId);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }

}
