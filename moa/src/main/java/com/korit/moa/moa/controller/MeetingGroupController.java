package com.korit.moa.moa.controller;

import com.korit.moa.moa.common.constant.ApiMappingPattern;
import com.korit.moa.moa.dto.ResponseDto;
import com.korit.moa.moa.dto.group.response.ResponseGroupDto;
import com.korit.moa.moa.dto.group.response.SearchResponseDto;
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

    private com.korit.moa.moa.service.meetingGroupService meetingGroupService;

//    @PostMapping
//    public ResponseEntity<ResponseDto<ResponseGroupDto>> createGroupMeeting(){
//
//    }
//
//    @PutMapping
//    public ResponseEntity<ResponseDto<ResponseGroupDto>>updateMeetingGroupId(){
//
//    }
//
//    @DeleteMapping
//    public ResponseEntity<ResponseDto<ResponseGroupDto>> deleteMeetingGroupId(){
//
//    }

    //
//    @GetMapping("{/userId}")
//    public ResponseEntity<ResponseDto<List<ResponseGroupDto>>> findHomeSelectByUserId(@AuthenticationPrincipal String userId) {
//        ResponseDto<List<ResponseGroupDto>> response = meetingGroupService.findHomeSelectByUserId(userId);
//        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
//        return ResponseEntity.status(status).body(response);
//    }

    @GetMapping("/groupTitle")
    public ResponseEntity<ResponseDto<List<SearchResponseDto>>> findByGroupTitle(@RequestParam("groupTitle") String groupTitle) {
        ResponseDto<List<SearchResponseDto>> response = meetingGroupService.findByGroupTitle(groupTitle);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status).body(response);
    }




}
