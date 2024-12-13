package com.korit.moa.moa.controller;

import com.korit.moa.moa.common.constant.ApiMappingPattern;
import com.korit.moa.moa.dto.ResponseDto;
import com.korit.moa.moa.dto.user_answer.request.RequestDeleteUserAnswerDto;
import com.korit.moa.moa.dto.user_answer.response.ResponseUserAnswerDto;
import com.korit.moa.moa.dto.user_list.request.UserLevelRequestDto;
import com.korit.moa.moa.dto.user_list.response.*;
import com.korit.moa.moa.entity.userList.UserListId;
import com.korit.moa.moa.service.UserListService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiMappingPattern.USER_LIST)
public class UserListController {

    private final UserListService userListService;

    public static final String USER_LIST = "/{groupId}";
    public static final String USER_DEL = "/van/{userListId}";
    public static final String USER_LEVEL = "/userLevel/{userId}";
    public static final String GENDER_CHART_PAGE = "/genderChart/{groupId}";
    public static final String USER_CHART_PAGE = "/userChart/{groupId}";


//     내 모임 조회
    @GetMapping
    public ResponseEntity<ResponseDto<List<GroupResponseDto>>> getMyGroups(
            @AuthenticationPrincipal String userId
    ) {
        ResponseDto<List<GroupResponseDto>> response = userListService.getMyGroups(userId);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }

    // 모임 내 유저리스트 조회
    @GetMapping(USER_LIST)
    public ResponseEntity<ResponseDto<List<UserListResponseDto>>> getUserList(
            @PathVariable Long groupId
    ) {
        ResponseDto<List<UserListResponseDto>> response = userListService.getUserList(groupId);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }

    // 모임 나가기
    @DeleteMapping
    public ResponseEntity<ResponseDto<Void>> deleteUserList(@RequestParam String userId, @RequestParam Long groupId) {
        ResponseDto<Void> response = userListService.deleteUserList(userId, groupId);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }

    //유저 레벨 수정
    @PutMapping(USER_LEVEL)
    public ResponseEntity<ResponseDto<UserLevelResponseDto>> putUserLevel(
            @PathVariable String userId, @RequestBody UserLevelRequestDto dto)
    {
        ResponseDto<UserLevelResponseDto> response = userListService.putUserLevel(userId,dto);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }

    //모임에서 추방
    @DeleteMapping(USER_DEL)
    public ResponseEntity<ResponseDto<Void>> deleteUser(@PathVariable UserListId userListId){
        ResponseDto<Void> response = userListService.deleteUser(userListId);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }

    //모입 내 성별 차트
    @GetMapping(GENDER_CHART_PAGE)
    public ResponseEntity<ResponseDto<List<UserGenderRatioResponseDto>>> getUserListGender(@PathVariable Long groupId){
        ResponseDto<List<UserGenderRatioResponseDto>> response = userListService.getUserListGender(groupId);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }

    //모입 유입률 차트
    @GetMapping(USER_CHART_PAGE)
    public ResponseEntity<ResponseDto<List<MonthRatioResponseDto>>> getMonthUserList(@PathVariable Long groupId){
        ResponseDto<List<MonthRatioResponseDto>> response = userListService.getMonthUserList(groupId);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }

}
