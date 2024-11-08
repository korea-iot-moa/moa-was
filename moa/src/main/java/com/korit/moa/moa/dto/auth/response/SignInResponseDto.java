package com.korit.moa.moa.dto.auth.response;

import com.korit.moa.moa.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SignInResponseDto {
    private User user;

    private String token;

    private int exprTime;

}
