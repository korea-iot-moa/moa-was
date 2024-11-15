package com.korit.moa.moa.dto.user_list.response;

import com.korit.moa.moa.entity.meetingGroup.MeetingGroup;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
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
