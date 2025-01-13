package com.korit.moa.moa.dto.user_answer.request;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RequestDeleteUserAnswerDto {
    @NotBlank
    private String userId;

    @NotBlank
    private int isApproved;
}
