package com.korit.moa.moa.dto.auth.response;

import com.korit.moa.moa.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FindIdResponseDto {

    private String userId;

    public FindIdResponseDto(User user) {
        this.userId = user.getUserId();
    }
}
