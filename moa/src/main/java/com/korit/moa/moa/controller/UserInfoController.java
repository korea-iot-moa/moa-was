package com.korit.moa.moa.controller;

import com.korit.moa.moa.common.constant.ApiMappingPattern;
import com.korit.moa.moa.dto.ResponseDto;
import com.korit.moa.moa.dto.user.request.DeleteUserRequestDto;
import com.korit.moa.moa.dto.user.request.RequestUserDto;
import com.korit.moa.moa.dto.user.request.UpdateUserRequestDto;
import com.korit.moa.moa.dto.user.response.ResponseUserDto;
import com.korit.moa.moa.entity.user.User;
import com.korit.moa.moa.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiMappingPattern.USER)
@RequiredArgsConstructor
public class UserInfoController {

    private static final String USER_INFO_PATH = "/{userId}";

    private final UserService userService;


    // 사용자 정보 조회
    @GetMapping(USER_INFO_PATH)
    public ResponseEntity<ResponseDto<ResponseUserDto>> findByUserId(@PathVariable String userId) {
        ResponseDto<ResponseUserDto> response = userService.findByUserId(userId);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }

    // 사용자 정보 업데이트
    @PutMapping(USER_INFO_PATH)
    public ResponseEntity<ResponseDto<ResponseUserDto>> updateByUserId(
           @AuthenticationPrincipal @PathVariable String userId,
            @RequestBody UpdateUserRequestDto dto) {
        ResponseDto<ResponseUserDto> response = userService.updateByUserId(userId, dto);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }

    // 사용자 탈퇴
    @DeleteMapping("/user")
    public ResponseEntity<ResponseDto<Void>> deleteUser(@Valid @AuthenticationPrincipal @RequestBody DeleteUserRequestDto dto) {

        ResponseDto<Void> response = userService.deleteUser(dto);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }



}
