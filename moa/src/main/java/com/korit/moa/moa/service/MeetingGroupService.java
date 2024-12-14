package com.korit.moa.moa.service;

import com.korit.moa.moa.dto.ResponseDto;
import com.korit.moa.moa.dto.group.request.GroupHomeFilterRequestDto;
import com.korit.moa.moa.dto.group.request.RequestGroupDto;
import com.korit.moa.moa.dto.group.response.ResponseGroupDto;
import com.korit.moa.moa.dto.group.response.SearchResponseDto;
import com.korit.moa.moa.entity.meetingGroup.GroupCategory;
import com.korit.moa.moa.entity.meetingGroup.GroupTypeCategory;

import java.util.List;

public interface MeetingGroupService {

    //모임 생성
    ResponseDto<ResponseGroupDto> createGroupMeeting(String userId, RequestGroupDto dto);

    //모임 수정
    ResponseDto<ResponseGroupDto> updateMeetingGroupId(String userID,Long groupId, RequestGroupDto dto);

    //모임 삭제
    ResponseDto<Void> deleteMeetingGroupId(String userId,Long groupId);

    // 모임 홈화면카테고리별 모임 추천
    //    ResponseDto<List<ResponseGroupDto>> findHomeSelectByUserId(String userId);

    // 모임이름 검색 필터
    ResponseDto<List<SearchResponseDto>> findByGroupTitle(String keyword);

    //단기/정기 모임 필터
    ResponseDto<List<ResponseGroupDto>> findByGroupType(GroupTypeCategory groupType);

    //취미/지역 카테고리 필터
    ResponseDto<List<SearchResponseDto>> findByGroupCategoryAndRegion(GroupCategory groupCategory, String region);

    //홈화면 필터링(로그인)
//    ResponseDto<List<ResponseGroupDto>> findGroupByUserId(String userId, GroupHomeFilterRequestDto dto);

    //홈화면필터링(비회원)
    ResponseDto<List<ResponseGroupDto>> getGroupAtHomeAuth();
}