package com.korit.moa.moa.service;

import com.korit.moa.moa.dto.ResponseDto;
import com.korit.moa.moa.dto.group.request.RequestGroupDto;
import com.korit.moa.moa.dto.group.response.ResponseGroupDto;

public interface MeetingGroupService {
    //모임 생성
    ResponseDto<ResponseGroupDto> createGroupMeeting(String userId, RequestGroupDto dto);

    //모임 수정
    ResponseDto<ResponseGroupDto> updateMeetingGroupId(String userID,Long groupId, RequestGroupDto dto);

    //모임 삭제
    ResponseDto<Void> deleteMeetingGroupId(String userId,Long groupId);
}
