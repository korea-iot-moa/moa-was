package com.korit.moa.moa.dto.user_answer.response;

import com.korit.moa.moa.entity.userAnswer.UserAnswer;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseUserAnswerDto {

    @NotBlank
    private Long groupId;

    @NotBlank
    private String userId;

    @NotBlank
    private int isApproved = 2;

    public ResponseUserAnswerDto(UserAnswer userAnswer) {
        this.groupId = userAnswer.getGroupId();
        this.userId = userAnswer.getUserId();
        this.isApproved = userAnswer.getIsApproved();
    }

}