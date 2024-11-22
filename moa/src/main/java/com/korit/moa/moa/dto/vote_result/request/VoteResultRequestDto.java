package com.korit.moa.moa.dto.vote_result.request;

import com.korit.moa.moa.entity.voteResult.VoteAnswer;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class VoteResultRequestDto {

    @NotNull
    private Long voteId;

    @NotBlank
    private String userId;

    @NotBlank
    private VoteAnswer voteAnswer;

    private Date voteDate = new Date();
}
