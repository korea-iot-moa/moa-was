package com.korit.moa.moa.dto.user_list.response;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserListResponseDto {
    @NotBlank
    private String userId;

    @NotBlank
    private String nickName;

    @NotBlank
    private String profileImage;
}
