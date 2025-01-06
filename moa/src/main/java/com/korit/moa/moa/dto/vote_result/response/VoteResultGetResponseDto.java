package com.korit.moa.moa.dto.vote_result.response;

import com.korit.moa.moa.entity.voteResult.VoteAnswer;
import com.korit.moa.moa.entity.voteResult.VoteResult;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class VoteResultGetResponseDto {
    @NotBlank
    private String voteAnswer;

    private  long count; 
    
    private double ratio;


}
