package com.korit.moa.moa.dto.user_list.response;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GroupResponseDto {

    @NotBlank
    private Long groupId;

    @NotBlank
    private String groupTitle;

    @NotBlank
    private String groupImage;


}
