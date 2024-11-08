package com.korit.moa.moa.service.implement;

import com.korit.moa.moa.common.constant.ApiMappingPattern;
import com.korit.moa.moa.dto.ResponseDto;
import com.korit.moa.moa.dto.auth.request.SignInRequestDto;
import com.korit.moa.moa.dto.auth.response.SignInResponseDto;
import com.korit.moa.moa.dto.auth.response.SignUpResponsetDto;
import com.korit.moa.moa.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Service
public class AuthServiceImplement implements AuthService {


    @Override
    public ResponseDto<SignUpResponsetDto> signUp(SignUpResponsetDto dto) {
        return null;
    }

    @Override
    public ResponseDto<SignInResponseDto> signIn(SignInRequestDto dto) {
        return null;
    }
}
