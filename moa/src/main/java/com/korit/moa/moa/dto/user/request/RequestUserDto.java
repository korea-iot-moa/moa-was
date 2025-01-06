package com.korit.moa.moa.dto.user.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RequestUserDto {

    @NotNull
    private String password;
}
