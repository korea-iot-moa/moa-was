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
    private static final String EXISTS_CREATOR = "/exists/{groupId}";
    private static final String GET_MEETING_GROUP = "/{groupId}";


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

    // 홈화면 카테고리별 추천 모임
    @GetMapping(GET_MEETINGGROUP_HOME)
    public ResponseEntity<ResponseDto<List<SearchResponseDto>>> getGroupAtHome(
            @AuthenticationPrincipal String userId
    ) {
        ResponseDto<List<SearchResponseDto>> response = meetingGroupService.getGroupAtHome(userId);
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

    // 모임 단건 조회
    @GetMapping(GET_MEETING_GROUP)
    public ResponseEntity<ResponseDto<ResponseGroupDto>> findGroup(@PathVariable Long groupId) {
        ResponseDto<ResponseGroupDto> response = meetingGroupService.findGroup(groupId);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }

}