package com.korit.moa.moa.dto.user_answer.response;

import com.korit.moa.moa.entity.userAnswer.UserAnswer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseUserAnswerDto {

    private Long answerId;

    private Long groupId;

    private String userId;

    private String userAnswer;

    private LocalDate answerDate;

    private boolean isApproved;


    public ResponseUserAnswerDto(UserAnswer userAnswers) {
        this.answerId = userAnswers.getAnswerId();
        this.groupId = userAnswers.getGroupId();
        this.userId = userAnswers.getUserId();
        this.userAnswer = userAnswers.getUserAnswer();
        this.answerDate = userAnswers.getAnswerDate();
    }
}
