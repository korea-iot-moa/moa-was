package com.korit.moa.moa.service;

import com.korit.moa.moa.dto.ResponseDto;
import com.korit.moa.moa.dto.user.request.DeleteUserRequestDto;
import com.korit.moa.moa.dto.user.request.RequestUserDto;
import com.korit.moa.moa.dto.user.request.UpdateUserRequestDto;
import com.korit.moa.moa.dto.user.response.ResponseUserDto;
import jakarta.validation.Valid;

public interface UserService {

    ResponseDto<ResponseUserDto> findByUserId(@Valid String userId);

    ResponseDto<ResponseUserDto> updateByUserId(String userId, UpdateUserRequestDto dto);

    ResponseDto<Void> deleteUser(@Valid DeleteUserRequestDto dto);
}
