package com.korit.moa.moa.service;

import com.korit.moa.moa.dto.ResponseDto;
import com.korit.moa.moa.dto.vote.request.RequestUpdateVoteDto;
import com.korit.moa.moa.dto.vote.request.RequestVoteDto;
import com.korit.moa.moa.dto.vote.response.PostVoteResponseDto;
import com.korit.moa.moa.dto.vote.response.VoteResponseDto;

public interface VoteService {
    ResponseDto<VoteResponseDto> getMyGroupVote(Long groupId);
    ResponseDto<PostVoteResponseDto> postMyGroupVote(RequestVoteDto dto, String userId);
    ResponseDto<VoteResponseDto> updateMyGroupVote(Long voteId, RequestUpdateVoteDto dto);
    ResponseDto<Void> deleteMyGroupVote(Long voteId);

    ResponseDto<Boolean> existsVote(Long groupId);
}
