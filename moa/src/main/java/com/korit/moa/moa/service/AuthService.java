package com.korit.moa.moa.service;

import com.korit.moa.moa.dto.ResponseDto;
import com.korit.moa.moa.dto.auth.request.FindIdRequestDto;
import com.korit.moa.moa.dto.auth.request.SignInRequestDto;
import com.korit.moa.moa.dto.auth.request.SignUpRequestDto;
import com.korit.moa.moa.dto.auth.response.FindIdResponseDto;
import com.korit.moa.moa.dto.auth.response.SignInResponseDto;
import com.korit.moa.moa.dto.auth.response.SignUpResponseDto;
import com.korit.moa.moa.dto.review.response.ReviewResponseDto;
import com.korit.moa.moa.entity.user.Hobby;
import jakarta.validation.Valid;

import java.util.Date;
import java.util.List;

public interface AuthService {
    ResponseDto<SignUpResponseDto> signUp (SignUpRequestDto dto);
    ResponseDto<SignInResponseDto> signIn (SignInRequestDto dto);
    ResponseDto<FindIdResponseDto> findLoginId(String userName, Date userBirthDate);
    ResponseDto<List<Hobby>> getHobbies();
    ResponseDto<Boolean> duplicateId(@Valid String userId);
    ResponseDto<Boolean> duplicateNickName(@Valid String nickName);
}
