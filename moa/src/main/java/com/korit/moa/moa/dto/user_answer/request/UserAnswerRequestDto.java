package com.korit.moa.moa.dto.user_answer.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserAnswerRequestDto {

    @NotNull
    private Long groupId;

    @NotNull
    private String userAnswer;
}
