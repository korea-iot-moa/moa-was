package com.korit.moa.moa.dto.vote.response;

import com.korit.moa.moa.entity.votes.Votes;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostVoteResponseDto {

    @NotBlank
    private Long voteId;

    @NotBlank
    private Long groupId;

    @NotBlank
    private String creatorId;

    @NotBlank
    private String voteContent;

    @NotBlank
    private Date createDate;

    @NotBlank
    private Date closeDate;

    public PostVoteResponseDto(Votes votes) {
        this.voteId = votes.getVoteId();
        this.creatorId = votes.getCreatorId();
        this.voteContent = votes.getVoteContent();
        this.groupId = votes.getGroupId();
        this.createDate = votes.getCreateDate();
        this.closeDate = votes.getCloseDate();
    }
}
