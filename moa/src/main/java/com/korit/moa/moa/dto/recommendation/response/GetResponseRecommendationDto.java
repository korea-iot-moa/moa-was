package com.korit.moa.moa.dto.recommendation.response;

import com.korit.moa.moa.entity.meetingGroup.GroupCategory;
import com.korit.moa.moa.entity.meetingGroup.GroupTypeCategory;
import com.korit.moa.moa.entity.meetingGroup.MeetingTypeCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetResponseRecommendationDto {
    private Long groupId;

    private String userId;

    private String groupTitle;

    private String groupContent;

    private String groupAddress;

    private String  groupImage;

    private String groupSupplies;

    private String groupDate;

    private GroupCategory groupCategory;

    private GroupTypeCategory groupType;

    private MeetingTypeCategory meetingType;

    public GetResponseRecommendationDto(Object[] objects) {
        this.groupId = objects[0] != null ? ((Number) objects[0]).longValue() : null;
        this.userId = objects[1] != null ? objects[1].toString() : null;
        this.groupTitle = objects[2] != null ? objects[2].toString() : null;
        this.groupContent = objects[3] != null ? objects[3].toString() : null;
        this.groupAddress = objects[4] != null ? objects[4].toString() : null;
        this.groupImage = objects[5] != null ? objects[5].toString() : null;
        this.groupSupplies = objects[6] != null ? objects[6].toString() : null;
        this.groupDate = objects[7] != null ? objects[7].toString() : null;
        this.groupCategory = objects[8] != null ? GroupCategory.valueOf(objects[8].toString()) : null;
        this.groupType = objects[9] != null ? GroupTypeCategory.valueOf(objects[9].toString()) : null;
        this.meetingType = objects[10] != null ? MeetingTypeCategory.valueOf(objects[10].toString()) : null;
    }

}
