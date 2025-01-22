package com.korit.moa.moa.dto.user_answer.response;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class UserAnswerGetResponseDto {
    @NotBlank
    private Long groupId;

    @NotBlank
    private String groupTitle;

    @NotBlank
    private String userId;

    @NotBlank
    private int isApproved = 2;


    public UserAnswerGetResponseDto(Object[] objects) {
        this.groupId = objects[0] != null ? ((Number) objects[0]).longValue() : null;

        this.groupTitle = (String) objects[1];

        this.userId = (String) objects[2];

        this.isApproved = objects[3] != null ? ((Number) objects[3]).intValue() : 2;
    }
}
