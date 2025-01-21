package com.korit.moa.moa.service;

import com.korit.moa.moa.dto.ResponseDto;
import com.korit.moa.moa.dto.group.request.GroupHomeFilterRequestDto;
import com.korit.moa.moa.dto.group.request.RequestGroupDto;
import com.korit.moa.moa.dto.group.response.HomeGroupResponseDto;
import com.korit.moa.moa.dto.group.response.ResponseGroupDto;
import com.korit.moa.moa.dto.group.response.SearchResponseDto;
import com.korit.moa.moa.entity.meetingGroup.GroupCategory;
import com.korit.moa.moa.entity.meetingGroup.GroupTypeCategory;
import com.korit.moa.moa.entity.user.User;

import java.util.List;

public interface MeetingGroupService {

    //모임 생성
    ResponseDto<ResponseGroupDto> createGroupMeeting(String userId, RequestGroupDto dto);

    //모임 수정
    ResponseDto<ResponseGroupDto> updateMeetingGroupId(Long groupId, RequestGroupDto dto);

    //모임 삭제
    ResponseDto<Void> deleteMeetingGroupId(Long groupId);

    // 모임이름 검색 필터
    ResponseDto<List<SearchResponseDto>> findByGroupTitle(String keyword);

    //단기/정기 모임 필터
    ResponseDto<List<SearchResponseDto>> findByGroupType(GroupTypeCategory groupType);

    //취미/지역 카테고리 필터
    ResponseDto<List<SearchResponseDto>> findByGroupCategoryAndRegion(GroupCategory groupCategory, String region);

    //홈화면 필터링(로그인)
    ResponseDto<List<HomeGroupResponseDto>> getGroupAtHome(String userId);

    //홈화면필터링(비회원)
    ResponseDto<List<HomeGroupResponseDto>> getGroupAtHomeAuth();

    // 그룹 단건 조회
    ResponseDto<ResponseGroupDto> getGroupById(Long groupId);

    ResponseDto<Boolean> isCreator(Long groupId, String userId);

    ResponseDto<ResponseGroupDto> findGroup(Long groupId);
}