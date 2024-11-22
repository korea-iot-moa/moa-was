package com.korit.moa.moa.dto.vote.response;

import com.korit.moa.moa.entity.votes.Votes;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class VoteResponseDto {

    private Long voteId;

    private Long groupId;

    private String creatorId;

    private String voteContent;

    private String createDate;

    private String closeDate;

    public VoteResponseDto(Votes vote) {
        this.voteId = vote.getVoteId();
        this.groupId = vote.getGroupId();
        this.creatorId = vote.getCreatorId();
        this.voteContent = vote.getVoteContent();
        this.createDate = vote.getCreateDate().toString();
        this.closeDate = vote.getCloseDate().toString();
    }
}
