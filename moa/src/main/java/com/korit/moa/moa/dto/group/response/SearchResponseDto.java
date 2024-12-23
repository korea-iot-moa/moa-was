package com.korit.moa.moa.dto.group.response;

import com.korit.moa.moa.entity.meetingGroup.GroupCategory;
import com.korit.moa.moa.entity.meetingGroup.GroupTypeCategory;
import com.korit.moa.moa.entity.meetingGroup.MeetingGroup;
import com.korit.moa.moa.entity.meetingGroup.MeetingTypeCategory;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SearchResponseDto {


    private Long groupId;

    private String  creatorId;

    private String groupTitle;

    private String groupContent;

    private String groupAddress;

    private String  groupImage;

    private String groupSupplies;

    private String groupDate;

    private String groupQuestion;

    private GroupCategory groupCategory;

    private GroupTypeCategory groupType;

    private MeetingTypeCategory meetingType;

    public SearchResponseDto(MeetingGroup result) {
        this.groupId = result.getGroupId();
        this.creatorId = result.getCreatorId();
        this.groupTitle = result.getGroupTitle();
        this.groupContent = result.getGroupContent();
        this.groupSupplies = result.getGroupSupplies();
        this.groupAddress = result.getGroupAddress();
        this.groupCategory = result.getGroupCategory();
        this.groupType = result.getGroupType();
        this.groupQuestion = result.getGroupQuestion();
        this.groupImage = result.getGroupImage();
        this.groupDate = result.getGroupDate();
    }


}