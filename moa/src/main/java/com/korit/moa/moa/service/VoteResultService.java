package com.korit.moa.moa.service;

import com.korit.moa.moa.dto.ResponseDto;
import com.korit.moa.moa.dto.vote_result.request.VoteResultRequestDto;
import com.korit.moa.moa.dto.vote_result.response.VoteResultGetResponseDto;
import com.korit.moa.moa.dto.vote_result.response.VoteResultResponseDto;

import java.util.List;

public interface VoteResultService {

    ResponseDto<VoteResultResponseDto> createVoteResult(String userId, VoteResultRequestDto dto);

    ResponseDto<List<VoteResultGetResponseDto>> getVoteResult(Long groupId);

    ResponseDto<Boolean> existsByVoteIdAndUserId(String userId, Long voteId);
}
