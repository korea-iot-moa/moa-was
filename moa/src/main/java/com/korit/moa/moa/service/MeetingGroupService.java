package com.korit.moa.moa.service;

import com.korit.moa.moa.dto.ResponseDto;
import com.korit.moa.moa.dto.group.request.GroupHomeFilterRequestDto;
import com.korit.moa.moa.dto.group.request.RequestGroupDto;
import com.korit.moa.moa.dto.group.response.ResponseGroupDto;
import com.korit.moa.moa.dto.group.response.SearchResponseDto;
import com.korit.moa.moa.entity.meetingGroup.GroupCategory;
import com.korit.moa.moa.entity.meetingGroup.GroupTypeCategory;
import com.korit.moa.moa.entity.user.Region;

import java.util.List;

public interface MeetingGroupService {
    //모임 생성
    ResponseDto<ResponseGroupDto> createGroupMeeting(String userId, RequestGroupDto dto);

    //모임 수정
    ResponseDto<ResponseGroupDto> updateMeetingGroupId(String userID,Long groupId, RequestGroupDto dto);

    //모임 삭제
    ResponseDto<Void> deleteMeetingGroupId(String userId,Long groupId);

    ResponseDto<List<SearchResponseDto>> findByGroupTitle(String keyword);

    ResponseDto<List<ResponseGroupDto>> findByGroupType(GroupTypeCategory groupType);

    ResponseDto<List<SearchResponseDto>> findByGroupCategoryAndRegion(GroupCategory groupCategory, String region);

    ResponseDto<List<ResponseGroupDto>> findGroupByUserId(String userId, GroupHomeFilterRequestDto dto);

    ResponseDto<List<ResponseGroupDto>> getGroupAtHomeAuth();
}
