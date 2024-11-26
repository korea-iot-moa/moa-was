package com.korit.moa.moa.service;

import com.korit.moa.moa.dto.ResponseDto;
import com.korit.moa.moa.dto.group.request.RequestGroupDto;
import com.korit.moa.moa.dto.group.response.ResponseGroupDto;
import com.korit.moa.moa.dto.group.response.SearchResponseDto;

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
    ResponseDto<List<SearchResponseDto>> findByGroupTitle(String groupTitle);
}