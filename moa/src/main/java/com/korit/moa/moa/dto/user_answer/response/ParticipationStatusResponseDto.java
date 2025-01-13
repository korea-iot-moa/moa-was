package com.korit.moa.moa.dto.user_answer.response;

import com.korit.moa.moa.entity.meetingGroup.GroupCategory;
import com.korit.moa.moa.entity.meetingGroup.GroupTypeCategory;
import com.korit.moa.moa.entity.meetingGroup.MeetingGroup;
import com.korit.moa.moa.entity.meetingGroup.MeetingTypeCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.invoke.MethodType;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParticipationStatusResponseDto {
    private Long groupId;
    private String groupTitle;
    private GroupTypeCategory groupType;
    private MeetingTypeCategory meetingType;
    private GroupCategory groupCategory;
    private String groupImage;
    private Long answerId;
    private LocalDate answerDate;
    private int isApproved;
}