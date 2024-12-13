package com.korit.moa.moa.dto.vote.response;

import com.korit.moa.moa.entity.votes.Votes;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class VoteResponseDto {
    @NotBlank
    private Long voteId;

    @NotBlank
    private Long groupId;

    @NotBlank
    private String creatorId;

    @NotBlank
    private String voteContent;

    @NotBlank
    private String createDate;

    @NotBlank
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
