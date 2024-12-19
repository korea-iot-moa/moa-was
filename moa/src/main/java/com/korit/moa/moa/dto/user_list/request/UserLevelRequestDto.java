package com.korit.moa.moa.dto.user_list.request;

import com.korit.moa.moa.entity.userList.UserLevel;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLevelRequestDto {
    @NotBlank
    private String userId;

    @NotBlank
    private UserLevel userLevel;
}
