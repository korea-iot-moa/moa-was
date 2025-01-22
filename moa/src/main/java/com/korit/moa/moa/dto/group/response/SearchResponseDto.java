package com.korit.moa.moa.dto.group.response;

import com.korit.moa.moa.entity.meetingGroup.GroupCategory;
import com.korit.moa.moa.entity.meetingGroup.MeetingGroup;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SearchResponseDto {

    private Long groupId;

    private String groupTitle;

    private GroupCategory groupCategory;

    private String groupAddress;

    private String  groupImage;

    private String groupDate;

    private int recommendationCount;

    public SearchResponseDto(MeetingGroup result) {
        this.groupId = result.getGroupId();
        this.groupTitle = result.getGroupTitle();
        this.groupCategory = result.getGroupCategory();
        this.groupAddress = result.getGroupAddress();
        this.groupImage = result.getGroupImage();
        this.groupDate = result.getGroupDate();
        this.recommendationCount = result.getRecommendation().size();
    }


}