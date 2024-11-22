package com.korit.moa.moa.dto.group.response;

import com.korit.moa.moa.entity.meetingGroup.GroupCategory;
import com.korit.moa.moa.entity.meetingGroup.MeetingGroup;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseGroupDto {

    private Long groupId;

    private String groupTitle;

    private String groupAddress;

    private GroupCategory groupCategory;

    private String groupImage;

    private String groupDate;

    public ResponseGroupDto(MeetingGroup meetingGroup) {
        this.groupId = meetingGroup.getGroupId();
        this.groupTitle = meetingGroup.getGroupTitle();
        this.groupAddress = meetingGroup.getGroupAddress();
        this.groupCategory = meetingGroup.getGroupCategory();
        this.groupImage = meetingGroup.getGroupImage();
        this.groupDate = meetingGroup.getGroupDate();
    }
}
