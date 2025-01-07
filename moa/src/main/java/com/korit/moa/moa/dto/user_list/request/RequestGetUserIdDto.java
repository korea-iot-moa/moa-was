package com.korit.moa.moa.dto.user_list.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestGetUserIdDto {
    @NotNull
    private Long groupId;

    @NotNull
    private String userId;
}
