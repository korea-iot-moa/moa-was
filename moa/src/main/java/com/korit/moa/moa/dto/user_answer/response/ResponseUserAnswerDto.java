package com.korit.moa.moa.dto.user_answer.response;

import com.korit.moa.moa.entity.userAnswer.UserAnswer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseUserAnswerDto {

    private Long answerId;

    private Long groupId;

    private String userId;

    private int isApproved = 2;

    private String userAnswer;

    private LocalDate answerDate;

    public ResponseUserAnswerDto(UserAnswer userAnswer) {
        this.answerId = userAnswer.getAnswerId();
        this.groupId = userAnswer.getGroupId();
        this.userId = userAnswer.getUserId();
        this.isApproved = userAnswer.getIsApproved();
        this.userAnswer = userAnswer.getUserAnswer();
        this.answerDate = userAnswer.getAnswerDate();
    }

}