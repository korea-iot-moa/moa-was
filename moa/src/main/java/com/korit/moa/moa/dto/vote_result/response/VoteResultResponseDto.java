package com.korit.moa.moa.dto.vote_result.response;

import com.korit.moa.moa.entity.voteResult.VoteAnswer;
import com.korit.moa.moa.entity.voteResult.VoteResult;
import com.korit.moa.moa.entity.votes.Votes;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class VoteResultResponseDto {

    private Long voteResultId;

    private Long voteId;

    private String userId;

    private VoteAnswer voteAnswer;

    private Date voteDate;

    public VoteResultResponseDto(VoteResult voteResult) {
        this.voteResultId = voteResult.getVoteId();
        this.voteId = voteResult.getVoteResultId();
        this.userId = voteResult.getUserId();
        this.voteAnswer = voteResult.getVoteAnswer();
        this.voteDate = voteResult.getVoteDate();
    }
}
