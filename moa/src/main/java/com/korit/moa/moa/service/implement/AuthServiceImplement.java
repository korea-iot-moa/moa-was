package com.korit.moa.moa.service.implement;

import com.korit.moa.moa.common.constant.ResponseMessage;
import com.korit.moa.moa.dto.ResponseDto;
import com.korit.moa.moa.dto.auth.request.SignInRequestDto;
import com.korit.moa.moa.dto.auth.request.SignUpRequestDto;
import com.korit.moa.moa.dto.auth.response.SignInResponseDto;
import com.korit.moa.moa.dto.auth.response.SignUpResponseDto;
import com.korit.moa.moa.entity.user.Gender;
import com.korit.moa.moa.entity.user.Hobby;
import com.korit.moa.moa.entity.user.Region;
import com.korit.moa.moa.entity.user.User;
import com.korit.moa.moa.provider.JwtProvider;
import com.korit.moa.moa.repository.UserRepository;
import com.korit.moa.moa.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthServiceImplement implements AuthService {
    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public ResponseDto<SignUpResponseDto> signUp(SignUpRequestDto dto) {
        String userId = dto.getUserId();
        String password = dto.getPassword();
        String confirmPassword = dto.getConfirmPassword();
        String userName = dto.getUserName();
        String nickName = dto.getNickName();
        Gender userGender = dto.getUserGender();
        Date userBirthdate = dto.getUserBirthDate();
        Set<Hobby> hobbies = dto.getHobbies();
        String profileImage = dto.getProfileImage();
        Region region = dto.getRegion();

        // 아이디 유효성 검사
        if (userId == null || userId.isEmpty() || !userId.matches("^[a-zA-Z0-9]{8,14}$")) {
            return ResponseDto.setFailed(ResponseMessage.DUPLICATED_USER_ID);
        }

        // 비밀번호 유효성 검사
        if (password == null || password.isEmpty()
                || !password.matches("^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[\\W_])[a-zA-Z\\d\\W_]{8,16}$")) {
            return ResponseDto.setFailed(ResponseMessage.VALIDATION_FAIL);
        }

        // 비밀번호 확인 유효성 검사
        if (confirmPassword == null || confirmPassword.isEmpty()
                || !confirmPassword.matches("^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[\\W_])[a-zA-Z\\d\\W_]{8,16}$")) {
            return ResponseDto.setFailed(ResponseMessage.VALIDATION_FAIL);
        }

        // 비밀번호 일치 여부 확인
        if (!password.equals(confirmPassword)) {
            return ResponseDto.setFailed(ResponseMessage.NOT_MATCH_PASSWORD);
        }

        // 이름 유효성 검사
        if (userName == null || userName.isEmpty() || !userName.matches("^[a-zA-Z가-힣]+$")) {
            return ResponseDto.setFailed(ResponseMessage.VALIDATION_FAIL);
        }

        // 닉네임 유효성 검사
        if (nickName == null || nickName.isEmpty() || !nickName.matches("^[a-zA-Z가-힣0-9]{1,10}$")) {
            return ResponseDto.setFailed(ResponseMessage.VALIDATION_FAIL);
        }

        // 생년월일 유효성 검사
        if (userBirthdate == null) {
            return ResponseDto.setFailed(ResponseMessage.VALIDATION_FAIL);
        }

        // 취미 유효성 검사
        if (!hobbies.isEmpty() && hobbies.size() != 3) {
            return ResponseDto.setFailed(ResponseMessage.HOBBY_VALIDATION_FAILED);
        }

        try {
            String encodingPassword = bCryptPasswordEncoder.encode(password);
            User user = User.builder()
                    .userId(userId)
                    .password(encodingPassword)
                    .userName(userName)
                    .nickName(nickName)
                    .userGender(userGender)
                    .userBirthDate(userBirthdate)
                    .hobbies(hobbies)
                    .profileImage(profileImage)
                    .region(region)
                    .build();

            userRepository.save(user);
            SignUpResponseDto data = new SignUpResponseDto(user);
            return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
    }

    @Override
    public ResponseDto<SignInResponseDto> signIn(SignInRequestDto dto) {
        String userId = dto.getUserId();
        String password = dto.getPassword();
        SignInResponseDto data = null;

        // 아이디 유효성 검사
        if (userId == null || userId.isEmpty()) {
            return ResponseDto.setFailed(ResponseMessage.VALIDATION_FAIL);
        }

        // 비밀번호 유효성 검사
        if (password == null || password.isEmpty()) {
            return ResponseDto.setFailed(ResponseMessage.VALIDATION_FAIL);
        }

        try {
            User user = userRepository.findByUserId(userId)
                    .orElse(null);
            if (user == null) {
                return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_USER);
            }
            if (!bCryptPasswordEncoder.matches(password, user.getPassword())) {
                return ResponseDto.setFailed(ResponseMessage.NOT_MATCH_PASSWORD);
            }
            String token = jwtProvider.generateJwtToken(userId);
            int exprTime = jwtProvider.getExpiration();

            data = new SignInResponseDto(user, token, exprTime);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }
}
