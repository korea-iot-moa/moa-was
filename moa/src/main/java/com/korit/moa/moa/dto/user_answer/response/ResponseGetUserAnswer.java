package com.korit.moa.moa.dto.user_answer.response;

import com.korit.moa.moa.entity.userAnswer.UserAnswer;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseGetUserAnswer {
    @NotBlank
    private String groupTitle;

    @NotBlank
    private String userId;

    @NotBlank
    private int isApproved = 2;

    public ResponseGetUserAnswer(UserAnswer userAnswer) {
        this.groupTitle = getGroupTitle();
        this.userId = userAnswer.getUserId();
        this.isApproved = getIsApproved();
    }
}
