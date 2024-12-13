package com.korit.moa.moa.dto.vote.response;

import com.korit.moa.moa.entity.votes.Votes;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VotePostResponseDto {
    @NotBlank
    private String voteContent;

    @NotBlank
    private String createDate;

    @NotBlank
    private String closeDate;


    public VotePostResponseDto(Votes votes) {
        this.voteContent = votes.getVoteContent();
        this.createDate = votes.getCreateDate().toString();
        this.closeDate = votes.getCloseDate().toString();
    }
}
