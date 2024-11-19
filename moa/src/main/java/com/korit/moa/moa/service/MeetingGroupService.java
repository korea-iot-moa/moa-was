package com.korit.moa.moa.service;

import com.korit.moa.moa.dto.ResponseDto;
import com.korit.moa.moa.dto.group.request.RequestGroupDto;
import com.korit.moa.moa.dto.group.response.ResponseGroupDto;

public interface MeetingGroupService {
    ResponseDto<ResponseGroupDto> createMeetingGroup(String userId, RequestGroupDto dto);

    ResponseDto<ResponseGroupDto> updateMeetingGroup(String userID,Long groupId, RequestGroupDto dto);

    ResponseDto<ResponseGroupDto> deleteMeetingGroup(String userId,Long groupId);
}
