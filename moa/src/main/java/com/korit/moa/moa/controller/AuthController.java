package com.korit.moa.moa.controller;

import com.korit.moa.moa.common.constant.ApiMappingPattern;
import com.korit.moa.moa.dto.ResponseDto;
import com.korit.moa.moa.dto.auth.request.SignInRequestDto;
import com.korit.moa.moa.dto.auth.request.SignUpRequestDto;
import com.korit.moa.moa.dto.auth.response.SignInResponseDto;
import com.korit.moa.moa.dto.auth.response.SignUpResponseDto;
import com.korit.moa.moa.entity.user.Hobby;
import com.korit.moa.moa.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiMappingPattern.AUTH)
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService ;

    private static final String SIGN_UP_PATH ="/signup";
    private static final String SIGN_IN_PATH ="/signin";

    @PostMapping(SIGN_UP_PATH)
    public ResponseEntity<ResponseDto<SignUpResponseDto>> signUp(@ModelAttribute SignUpRequestDto dto) {
        ResponseDto<SignUpResponseDto> response = authService.signUp(dto);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }


    @PostMapping(SIGN_IN_PATH)
    public ResponseEntity<ResponseDto<SignInResponseDto>> signIn (@Valid @RequestBody SignInRequestDto dto){
        ResponseDto<SignInResponseDto> response = authService.signIn(dto);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.UNAUTHORIZED;
        return ResponseEntity.status(status).body(response);
    }

    @CrossOrigin
    @GetMapping("/hobbies")
    public ResponseEntity<ResponseDto<List<Hobby>>> getHobbies() {
        ResponseDto<List<Hobby>> response = authService.getHobbies();
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }

//    @GetMapping("/userId")
//    public ResponseEntity<ResponseDto<FindIdResponseDto>> findLoginId(
//            @Valid @RequestParam String userName,
//            @Valid @RequestParam @DateTimeFormat(pattern = "yyyyMMdd") Date userBirthDate
//    ) {
//        ResponseDto<FindIdResponseDto> response = authService.findLoginId(userName, userBirthDate);
//        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
//        return ResponseEntity.status(status).body(response);
//    }

    @GetMapping("/duplicate-id/{userId}")
    public ResponseEntity<ResponseDto<Boolean>> duplicateId(@Valid @PathVariable String userId){
        ResponseDto<Boolean> response = authService.duplicateId(userId);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }

    @GetMapping("/duplicate-nickname/{nickName}")
    public ResponseEntity<ResponseDto<Boolean>> duplicateNickName(@Valid @PathVariable String nickName){
        ResponseDto<Boolean> response = authService.duplicateNickName(nickName);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }




}
