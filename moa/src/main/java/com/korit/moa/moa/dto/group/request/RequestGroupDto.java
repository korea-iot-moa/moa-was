package com.korit.moa.moa.dto.group.request;

import com.korit.moa.moa.entity.user.User;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RequestGroupDto {
    @NotBlank
    private User user;
}
