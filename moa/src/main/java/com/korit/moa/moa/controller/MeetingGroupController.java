package com.korit.moa.moa.controller;

import com.korit.moa.moa.common.constant.ApiMappingPattern;
import com.korit.moa.moa.dto.ResponseDto;
import com.korit.moa.moa.dto.group.request.RequestGroupDto;
import com.korit.moa.moa.dto.group.response.ResponseGroupDto;
import com.korit.moa.moa.service.MeetingGroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiMappingPattern.GROUP)
@RequiredArgsConstructor
public class MeetingGroupController {

    private MeetingGroupService meetingGroupService;

    @PostMapping
    public ResponseEntity<ResponseDto<ResponseGroupDto>> createGroupMeeting(
            @AuthenticationPrincipal String userId , RequestGroupDto dto
    ){
        ResponseDto<ResponseGroupDto> response = meetingGroupService.createMeetingGroup(userId, dto);
        HttpStatus status = response.isResult() ? HttpStatus.OK: HttpStatus.BAD_REQUEST;
        return  ResponseEntity.status(status).body(response);
    }

    @PutMapping
    public ResponseEntity<ResponseDto<ResponseGroupDto>>updateMeetingGroupId(
          @AuthenticationPrincipal String userId, @PathVariable Long groupId, RequestGroupDto dto
    ){
        ResponseDto<ResponseGroupDto> response = meetingGroupService.updateMeetingGroup(userId,groupId, dto);
        HttpStatus status = response.isResult() ? HttpStatus.OK: HttpStatus.BAD_REQUEST;
        return  ResponseEntity.status(status).body(response);
    }

    @DeleteMapping
    public ResponseEntity<ResponseDto<ResponseGroupDto>> deleteMeetingGroupId(
        @AuthenticationPrincipal String userId, @PathVariable Long groupId){
        ResponseDto<ResponseGroupDto> response = meetingGroupService.deleteMeetingGroup(userId,groupId);
        HttpStatus status = response.isResult() ? HttpStatus.OK: HttpStatus.BAD_REQUEST;
        return  ResponseEntity.status(status).body(response);
    }


}
