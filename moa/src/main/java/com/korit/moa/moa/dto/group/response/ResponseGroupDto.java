package com.korit.moa.moa.dto.group.response;

import com.korit.moa.moa.entity.meetingGroup.GroupCategory;
import com.korit.moa.moa.entity.meetingGroup.GroupTypeCategory;
import com.korit.moa.moa.entity.meetingGroup.MeetingGroup;
import com.korit.moa.moa.entity.meetingGroup.MeetingTypeCategory;
import com.korit.moa.moa.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseGroupDto {

    private Long groupId;

    private String  creatorId;//string

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

    public ResponseGroupDto(MeetingGroup meetingGroup){
        this.groupId = meetingGroup.getGroupId();
        this.groupTitle = meetingGroup.getGroupTitle();
        this.creatorId = meetingGroup.getCreatorId().toString();
        this.groupContent = meetingGroup.getGroupContent();
        this.groupAddress = meetingGroup.getGroupAddress();
        this.groupImage = meetingGroup.getGroupImage();
        this.groupSupplies = meetingGroup.getGroupSupplies();
        this.groupDate = meetingGroup.getGroupDate();
        this.groupQuestion = meetingGroup.getGroupQuestion();
        this.groupCategory = meetingGroup.getGroupCategory();
        this.groupType = meetingGroup.getGroupType();
        this.meetingType = meetingGroup.getMeetingType();
    }


}