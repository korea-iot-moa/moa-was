package com.korit.moa.moa.dto.group.request;

import com.korit.moa.moa.entity.user.Hobby;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupHomeFilterRequestDto {

    @NotNull
    private String userId;

    private Hobby hobbies;
}
