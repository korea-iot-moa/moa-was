package com.korit.moa.moa.service;

import com.korit.moa.moa.dto.ResponseDto;
import com.korit.moa.moa.dto.user_list.response.GroupResponseDto;
import com.korit.moa.moa.dto.vote.request.RequestUpdateVoteDto;
import com.korit.moa.moa.dto.vote.request.RequestVoteDto;
import com.korit.moa.moa.dto.vote.response.VotePostResponseDto;
import com.korit.moa.moa.dto.vote.response.VoteResponseDto;

import java.util.List;

public interface VoteService {
    ResponseDto<VoteResponseDto> getMyGroupVote(Long groupId);
    ResponseDto<VotePostResponseDto> postMyGroupVote(Long groupId, RequestVoteDto dto);
    ResponseDto<VoteResponseDto> updateMyGroupVote(Long voteId, RequestUpdateVoteDto dto);
    ResponseDto<Void> deleteMyGroupVote(Long voteId);
}
