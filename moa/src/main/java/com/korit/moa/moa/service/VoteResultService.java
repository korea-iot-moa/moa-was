package com.korit.moa.moa.service;

import com.korit.moa.moa.dto.ResponseDto;
import com.korit.moa.moa.dto.vote_result.request.VoteResultRequestDto;
import com.korit.moa.moa.dto.vote_result.response.VoteResultResponseDto;

public interface VoteResultService {
    ResponseDto<VoteResultResponseDto> createVoteResult(String userId, VoteResultRequestDto dto);
}
