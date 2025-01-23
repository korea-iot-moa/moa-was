package com.korit.moa.moa.dto.auth.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FindIdRequestDto {

    @NotNull
    private String userName;

    @NotNull
    private String phoneNumber;

    @NotNull
    private String email;

}
