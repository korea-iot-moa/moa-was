package com.korit.moa.moa.dto.user_list.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RequestUserListDto {
    @NotBlank
    private String userId;

    @NotBlank
    private Long groupId;
}
