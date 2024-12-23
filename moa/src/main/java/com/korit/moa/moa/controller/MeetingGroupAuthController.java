package com.korit.moa.moa.controller;


import com.korit.moa.moa.common.constant.ApiMappingPattern;
import com.korit.moa.moa.dto.ResponseDto;
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
    private static final String GET_GROUP_CATEGORY = "/meeting-group/groupCategory";
    private static final String GET_GROUP_TYPE = "/meeting-group/groupType";
    private static final String GET_GROUP_HOME = "/meeting-group/group";

    // 모임이름 검색 필터링
    @GetMapping(GET_GROUP)
    public ResponseEntity<ResponseDto<List<SearchResponseDto>>> SearchGroupKeyword(@RequestParam("keyword") String groupTitle) {
        ResponseDto<List<SearchResponseDto>> response = meetingGroupService.findByGroupTitle(groupTitle);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status).body(response);
    }

    // 모임 취미카테고리,지역칸테고리 필터링
    @GetMapping(GET_GROUP_CATEGORY)
    public ResponseEntity<ResponseDto<List<SearchResponseDto>>> findByGroupCategoryAndRegion(
            @RequestParam GroupCategory groupCategory,
            @RequestParam String region
    ) {
        ResponseDto<List<SearchResponseDto>> response = meetingGroupService.findByGroupCategoryAndRegion(groupCategory, region);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status).body(response);
    }

    // 단기/정기 모임 필터
    @GetMapping(GET_GROUP_TYPE)
    public ResponseEntity<ResponseDto<List<ResponseGroupDto>>> filterGroupType(@RequestParam GroupTypeCategory groupType) {
        ResponseDto<List<ResponseGroupDto>> response = meetingGroupService.findByGroupType(groupType);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status).body(response);
    }

    // 홈화면 카테고리별 추천 모임
    @GetMapping(GET_GROUP_HOME)
    public ResponseEntity<ResponseDto<List<ResponseGroupDto>>> getGroupAtHomeAuth() {
        ResponseDto<List<ResponseGroupDto>> response = meetingGroupService.getGroupAtHomeAuth();
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }

}
