package com.korit.moa.moa.service;

import com.korit.moa.moa.dto.ResponseDto;
import com.korit.moa.moa.dto.auth.request.SignInRequestDto;
import com.korit.moa.moa.dto.auth.request.SignUpRequestDto;
import com.korit.moa.moa.dto.auth.response.SignInResponseDto;
import com.korit.moa.moa.dto.auth.response.SignUpResponseDto;

public interface AuthService {
    ResponseDto<SignUpResponseDto> signUp (SignUpRequestDto dto);
    ResponseDto<SignInResponseDto> signIn (SignInRequestDto dto);

}
