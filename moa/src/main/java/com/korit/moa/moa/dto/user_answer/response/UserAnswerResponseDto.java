package com.korit.moa.moa.dto.user_answer.response;

import com.korit.moa.moa.entity.userAnswer.UserAnswer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAnswerResponseDto {
    private Long answerId;

    private Long groupId;

    private String userId;

    private String userAnswer;

    private LocalDate answerDate;

    private boolean isApproved;


    public UserAnswerResponseDto(UserAnswer userAnswers) {
        this.answerId = userAnswers.getAnswerId();
        this.groupId = userAnswers.getGroupId();
        this.userId = userAnswers.getUserId();
        this.userAnswer = userAnswers.getUserAnswer();
        this.answerDate = userAnswers.getAnswerDate();
    }
}
