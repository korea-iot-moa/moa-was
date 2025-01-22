package com.korit.moa.moa.controller;


import com.korit.moa.moa.common.constant.ApiMappingPattern;
import com.korit.moa.moa.dto.ResponseDto;
import com.korit.moa.moa.dto.group.response.HomeGroupResponseDto;
import com.korit.moa.moa.dto.group.response.ResponseGroupDto;
import com.korit.moa.moa.dto.group.response.SearchResponseDto;
import com.korit.moa.moa.entity.meetingGroup.GroupCategory;
import com.korit.moa.moa.entity.meetingGroup.GroupTypeCategory;
import com.korit.moa.moa.service.MeetingGroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiMappingPattern.AUTH)
@RequiredArgsConstructor
public class MeetingGroupAuthController {

    private final MeetingGroupService meetingGroupService;

    private static final String GET_GROUP = "/meeting-group";
    private static final String GET_GROUP_CATEGORY = "/meeting-group/group-category";
    private static final String GET_GROUP_TYPE = "/meeting-group/group-type";
    private static final String GET_GROUP_HOME = "/meeting-group/group";
    private static final String GET_MEETING_GROUP_ID = "/meeting-group/{groupId}";

    @CrossOrigin
    @GetMapping(GET_GROUP)
    public ResponseEntity<ResponseDto<List<SearchResponseDto>>> SearchGroupKeyword(@RequestParam("keyword") String groupTitle) {
        ResponseDto<List<SearchResponseDto>> response = meetingGroupService.findByGroupTitle(groupTitle);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status).body(response);
    }

    @CrossOrigin
    @GetMapping(GET_GROUP_CATEGORY)
    public ResponseEntity<ResponseDto<List<SearchResponseDto>>> findByGroupCategoryAndRegion(
            @RequestParam GroupCategory groupCategory,
            @RequestParam String region
    ) {
        ResponseDto<List<SearchResponseDto>> response = meetingGroupService.findByGroupCategoryAndRegion(groupCategory, region);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status).body(response);
    }
    @CrossOrigin
    @GetMapping(GET_GROUP_TYPE)
    public ResponseEntity<ResponseDto<List<SearchResponseDto>>> filterGroupType(@RequestParam GroupTypeCategory groupType) {
        ResponseDto<List<SearchResponseDto>> response = meetingGroupService.findByGroupType(groupType);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status).body(response);
    }

    @GetMapping(GET_GROUP_HOME)
    public ResponseEntity<ResponseDto<List<HomeGroupResponseDto>>> getGroupAtHomeAuth() {
        ResponseDto<List<HomeGroupResponseDto>> response = meetingGroupService.getGroupAtHomeAuth();
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }

    @GetMapping(GET_MEETING_GROUP_ID)
    public ResponseEntity<ResponseDto<ResponseGroupDto>> getGroupById(@PathVariable Long groupId) {
        ResponseDto<ResponseGroupDto> response = meetingGroupService.getGroupById(groupId);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }

}
