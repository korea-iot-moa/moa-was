package com.korit.moa.moa.dto.group.request;

import com.korit.moa.moa.entity.meetingGroup.GroupCategory;
import com.korit.moa.moa.entity.meetingGroup.GroupTypeCategory;
import com.korit.moa.moa.entity.meetingGroup.MeetingTypeCategory;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestGroupDto {
    @NotBlank
    private Long groupId;

    private String creatorId;

    @NotBlank
    private String groupTitle;

    @NotBlank
    private String groupContent;

    @NotBlank
    private String groupAddress;

    private String  groupImage;

    private String groupSupplies;

    @NotBlank
    private String groupDate;

    @NotBlank
    private String groupQuestion;

    private GroupCategory groupCategory;

    private GroupTypeCategory groupType;

    private MeetingTypeCategory meetingType;


}
