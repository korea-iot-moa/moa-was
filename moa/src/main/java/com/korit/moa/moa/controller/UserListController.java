package com.korit.moa.moa.controller;

import com.korit.moa.moa.common.constant.ApiMappingPattern;
import com.korit.moa.moa.dto.ResponseDto;
import com.korit.moa.moa.dto.user_list.response.GroupResponseDto;
import com.korit.moa.moa.service.UserListService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiMappingPattern.USER_LIST)
public class UserListController {

    private final UserListService userListService;

    @GetMapping
    public ResponseEntity<ResponseDto<List<GroupResponseDto>>> getMyGroups(
            @AuthenticationPrincipal String userId
    ) {
        ResponseDto<List<GroupResponseDto>> response = userListService.getMyGroups(userId);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }
}
