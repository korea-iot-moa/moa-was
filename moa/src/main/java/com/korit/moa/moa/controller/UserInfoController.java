package com.korit.moa.moa.controller;

import com.korit.moa.moa.common.constant.ApiMappingPattern;
import com.korit.moa.moa.dto.ResponseDto;
import com.korit.moa.moa.dto.user.request.DeleteUserRequestDto;
import com.korit.moa.moa.dto.user.request.RequestUserDto;
import com.korit.moa.moa.dto.user.request.UpdateUserPasswordRequestDto;
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

    private static final String RESET_PASSWORD ="/resetPassword";
    private static final String USER_INFO = "/user-id";
    private static final String USER_INFO_PUT = "/user-info";
    private static final String USER_INFO_GET_DUPLICATION = "/duplication/{nickName}";


    private final UserService userService;


    // 사용자 정보 조회
    @PostMapping(USER_INFO)
    public ResponseEntity<ResponseDto<ResponseUserDto>> findUserInfo(
            @AuthenticationPrincipal String userId,
            @RequestBody RequestUserDto dto
    ) {
        String password = dto.getPassword();

        ResponseDto<ResponseUserDto> response = userService.findUserInfo(userId, password);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }

    // 사용자 정보 업데이트
    @PutMapping(USER_INFO_PUT)
    public ResponseEntity<ResponseDto<ResponseUserDto>> updateUser(
           @AuthenticationPrincipal String userId,
           @ModelAttribute UpdateUserRequestDto dto) {
        ResponseDto<ResponseUserDto> response = userService.updateUser(userId, dto);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }

    // 사용자 탈퇴
    @DeleteMapping("/user")
    public ResponseEntity<ResponseDto<Void>> deleteUser(
            @Valid @AuthenticationPrincipal String userId,
            @RequestBody DeleteUserRequestDto dto) {

        ResponseDto<Void> response = userService.deleteUser(userId, dto);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }

    // 닉네임 중복확인
    @GetMapping(USER_INFO_GET_DUPLICATION)
    public ResponseEntity<ResponseDto<Boolean>> duplicationNickName(
            @PathVariable String nickName
    ) {
        ResponseDto<Boolean> response = userService.duplicationNickName(nickName);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }

    @PutMapping(RESET_PASSWORD)
    public ResponseEntity<ResponseDto<Boolean>> resetPassword(
            @AuthenticationPrincipal String userId,
            @RequestBody UpdateUserPasswordRequestDto dto
    ) {
        ResponseDto<Boolean> response = userService.resetPassword(userId, dto);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }

}
