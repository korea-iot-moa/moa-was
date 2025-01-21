package com.korit.moa.moa.service;

import com.korit.moa.moa.dto.ResponseDto;
import com.korit.moa.moa.dto.user.request.DeleteUserRequestDto;
import com.korit.moa.moa.dto.user.request.RequestUserDto;
import com.korit.moa.moa.dto.user.request.UpdateUserPasswordRequestDto;
import com.korit.moa.moa.dto.user.request.UpdateUserRequestDto;
import com.korit.moa.moa.dto.user.response.ResponseUserDto;
import jakarta.validation.Valid;

public interface UserService {

    ResponseDto<ResponseUserDto> findUserInfo(@Valid String userId);

    ResponseDto<ResponseUserDto> updateUser(String userId, UpdateUserRequestDto dto);

    ResponseDto<Boolean> resetPassword(String userId, UpdateUserPasswordRequestDto dto);

    ResponseDto<Void> deleteUser(@Valid String userId, DeleteUserRequestDto dto);

    ResponseDto<Boolean> duplicationNickName(String nickName);

    ResponseDto<Boolean> matchPassword(String userId, RequestUserDto dto);
}
