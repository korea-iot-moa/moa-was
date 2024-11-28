package com.korit.moa.moa.controller;

import com.korit.moa.moa.common.constant.ApiMappingPattern;
import com.korit.moa.moa.dto.ResponseDto;
import com.korit.moa.moa.dto.group.request.RequestGroupDto;
import com.korit.moa.moa.dto.group.response.ResponseGroupDto;
import com.korit.moa.moa.dto.group.response.SearchResponseDto;
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

    @PostMapping
    public ResponseEntity<ResponseDto<ResponseGroupDto>> createGroupMeeting(
            @AuthenticationPrincipal String userId , @RequestBody RequestGroupDto dto
    ){
        ResponseDto<ResponseGroupDto> response = meetingGroupService.createGroupMeeting(userId, dto);
        HttpStatus status = response.isResult() ? HttpStatus.OK: HttpStatus.BAD_REQUEST;
        return  ResponseEntity.status(status).body(response);
    }

    @PutMapping(UPD_MEETINGGROUP)
    public ResponseEntity<ResponseDto<ResponseGroupDto>>updateMeetingGroupId(
          @AuthenticationPrincipal String userId, @PathVariable Long groupId, @RequestBody RequestGroupDto dto
    ){
        ResponseDto<ResponseGroupDto> response = meetingGroupService.updateMeetingGroupId(userId,groupId, dto);
        HttpStatus status = response.isResult() ? HttpStatus.OK: HttpStatus.BAD_REQUEST;
        return  ResponseEntity.status(status).body(response);
    }

    @DeleteMapping(DEL_MEETINGGROUP)
    public ResponseEntity<ResponseDto<Void>> deleteMeetingGroupId(
        @AuthenticationPrincipal String userId, @PathVariable Long groupId){
        ResponseDto<Void> response = meetingGroupService.deleteMeetingGroupId(userId,groupId);
        HttpStatus status = response.isResult() ? HttpStatus.OK: HttpStatus.BAD_REQUEST;
        return  ResponseEntity.status(status).body(response);
    }

    // 홈화면 카테고리별 추천 모임
//    @GetMapping("{/userId}")
//    public ResponseEntity<ResponseDto<List<ResponseGroupDto>>> findHomeSelectByUserId(@AuthenticationPrincipal String userId) {
//        ResponseDto<List<ResponseGroupDto>> response = MeetingGroupService.findHomeSelectByUserId(userId);
//        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
//        return ResponseEntity.status(status).body(response);
//    }

    // 모임이름 검색 필터링
    @GetMapping("/groupTitle")
    public ResponseEntity<ResponseDto<List<SearchResponseDto>>> findByGroupTitle(@RequestParam("groupTitle") String groupTitle) {
        ResponseDto<List<SearchResponseDto>> response = meetingGroupService.findByGroupTitle(groupTitle);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status).body(response);
    }
}