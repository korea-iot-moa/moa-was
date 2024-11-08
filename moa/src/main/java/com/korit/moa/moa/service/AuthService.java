package com.korit.moa.moa.service;

import com.korit.moa.moa.dto.ResponseDto;
import com.korit.moa.moa.dto.auth.request.SignInRequestDto;
import com.korit.moa.moa.dto.auth.response.SignInResponseDto;
import com.korit.moa.moa.dto.auth.response.SignUpResponsetDto;

public interface AuthService {
    ResponseDto<SignUpResponsetDto> signUp (SignUpResponsetDto dto);
    ResponseDto<SignInResponseDto> signIn (SignInRequestDto dto);

}
