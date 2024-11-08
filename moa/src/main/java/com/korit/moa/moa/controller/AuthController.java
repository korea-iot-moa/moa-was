package com.korit.moa.moa.controller;

import com.korit.moa.moa.common.constant.ApiMappingPattern;
import com.korit.moa.moa.dto.ResponseDto;
import com.korit.moa.moa.dto.auth.request.SignUpRequestDto;
import com.korit.moa.moa.dto.auth.response.SignUpResponsetDto;
import com.korit.moa.moa.service.implement.AuthServiceImplement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiMappingPattern.AUTH)
public class AuthController {
    private final AuthServiceImplement authServiceImplement;

    private static final String SIGN_UP = "/signUp";
    private static final String SIGN_IN = "/signIn";

    @PostMapping(SIGN_UP)
    public ResponseEntity<ResponseDto<SignUpResponsetDto>> signUp (@Valid @RequestBody SignUpRequestDto dto) {
        ResponseDto<SignUpResponsetDto> response = authServiceImplement.signUp(dto);

    }


}
