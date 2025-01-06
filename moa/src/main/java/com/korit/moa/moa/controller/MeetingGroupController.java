package com.korit.moa.moa.controller;

import com.korit.moa.moa.common.constant.ApiMappingPattern;
import com.korit.moa.moa.dto.ResponseDto;
import com.korit.moa.moa.dto.group.request.GroupHomeFilterRequestDto;
import com.korit.moa.moa.dto.group.request.RequestGroupDto;
import com.korit.moa.moa.dto.group.response.ResponseGroupDto;
import com.korit.moa.moa.dto.group.response.SearchResponseDto;
import com.korit.moa.moa.entity.meetingGroup.GroupCategory;
import com.korit.moa.moa.entity.meetingGroup.GroupTypeCategory;
import com.korit.moa.moa.entity.user.User;
import com.korit.moa.moa.service.MeetingGroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiMappingPattern.GROUP)
@RequiredArgsConstructor
public class MeetingGroupController {

    private final MeetingGroupService meetingGroupService;
    private static final String UPD_MEETINGGROUP = "/{groupId}";
    private static final String DEL_MEETINGGROUP = "/{groupId}";
    private static final String GET_MEETINGGROUP_HOME = "/home-recommendation";
    private static final String GET_MEETINGGROUP_CATEGORY = "/groupCategory";
    private static final String GET_MEETINGGROUP_TYPE = "/groupType";
    private static final String GET_MEETINGGROUP_ID = "/{groupId}";
    private static final String EXISTS_CREATOR = "/exists/{groupId}";


    // 모임 생성
    @PostMapping
    public ResponseEntity<ResponseDto<ResponseGroupDto>> createGroupMeeting(
            @AuthenticationPrincipal String userId, @RequestBody RequestGroupDto dto
    ) {
        ResponseDto<ResponseGroupDto> response = meetingGroupService.createGroupMeeting(userId, dto);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }

    // 모임 수정
    @PutMapping(UPD_MEETINGGROUP)
    public ResponseEntity<ResponseDto<ResponseGroupDto>> updateMeetingGroupId(
            @PathVariable Long groupId, @RequestBody RequestGroupDto dto
    ) {
        ResponseDto<ResponseGroupDto> response = meetingGroupService.updateMeetingGroupId(groupId, dto);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }

    // 모임 삭제
    @DeleteMapping(DEL_MEETINGGROUP)
    public ResponseEntity<ResponseDto<Void>> deleteMeetingGroupId(@PathVariable Long groupId) {
        ResponseDto<Void> response = meetingGroupService.deleteMeetingGroupId(groupId);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }

    // 모임이름 검색 필터링
    @GetMapping
    public ResponseEntity<ResponseDto<List<SearchResponseDto>>> SearchGroupKeyword(@RequestParam("keyword") String groupTitle) {
        ResponseDto<List<SearchResponseDto>> response = meetingGroupService.findByGroupTitle(groupTitle);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status).body(response);
    }

    // 모임 취미카테고리,지역칸테고리 필터링
    @GetMapping(GET_MEETINGGROUP_CATEGORY)
    public ResponseEntity<ResponseDto<List<SearchResponseDto>>> findByGroupCategoryAndRegion(
            @RequestParam GroupCategory groupCategory,
            @RequestParam String region
    ) {
        ResponseDto<List<SearchResponseDto>> response = meetingGroupService.findByGroupCategoryAndRegion(groupCategory, region);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status).body(response);
    }

    // 단기/정기 모임 필터
    @GetMapping(GET_MEETINGGROUP_TYPE)
    public ResponseEntity<ResponseDto<List<SearchResponseDto>>> filterGroupType(@RequestParam GroupTypeCategory groupType) {
        ResponseDto<List<SearchResponseDto>> response = meetingGroupService.findByGroupType(groupType);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status).body(response);
    }

//     홈화면 카테고리별 추천 모임
    @GetMapping(GET_MEETINGGROUP_HOME)
    public ResponseEntity<ResponseDto<List<SearchResponseDto>>> getGroupAtHome(
            @AuthenticationPrincipal String userId
    ) {
        ResponseDto<List<SearchResponseDto>> response = meetingGroupService.getGroupAtHome(userId);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }

    // 모임 id로 단건 조회
    @GetMapping(GET_MEETINGGROUP_ID)
    public ResponseEntity<ResponseDto<ResponseGroupDto>> getGroupById(@PathVariable Long groupId) {
        ResponseDto<ResponseGroupDto> response = meetingGroupService.getGroupById(groupId);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }

    // 모임 관리자 확인
    @GetMapping(EXISTS_CREATOR)
    public ResponseEntity<ResponseDto<Boolean>> isCreator(
            @PathVariable Long groupId,
            @AuthenticationPrincipal String userId
    ) {
        ResponseDto<Boolean> response = meetingGroupService.isCreator(groupId, userId);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }

}