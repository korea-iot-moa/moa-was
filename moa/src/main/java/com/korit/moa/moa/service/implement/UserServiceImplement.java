package com.korit.moa.moa.service.implement;

import com.korit.moa.moa.common.constant.ResponseMessage;
import com.korit.moa.moa.dto.ResponseDto;
import com.korit.moa.moa.dto.user.request.DeleteUserRequestDto;
import com.korit.moa.moa.dto.user.request.RequestUserDto;
import com.korit.moa.moa.dto.user.request.UpdateUserPasswordRequestDto;
import com.korit.moa.moa.dto.user.request.UpdateUserRequestDto;
import com.korit.moa.moa.dto.user.response.ResponseUserDto;
import com.korit.moa.moa.entity.user.User;
import com.korit.moa.moa.repository.UserHobbiesRepository;
import com.korit.moa.moa.repository.UserRepository;
import com.korit.moa.moa.service.ImgFileService;
import com.korit.moa.moa.service.UserService;
import com.zaxxer.hikari.HikariConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImplement implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ImgFileService imgFileService;
    private final UserHobbiesRepository userHobbiesRepository;

    // 내정보 조회
    public ResponseDto<ResponseUserDto> findUserInfo(String userId ) {

        try {
            // 사용자 조회
            Optional<User> optionalUser = userRepository.findByUserId(userId);

            if (optionalUser.isEmpty()) {
                return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_DATA); // 사용자 없음
            }

            User user = optionalUser.get();

            // 성공적으로 데이터 설정
            ResponseUserDto data = new ResponseUserDto(user);
            return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR); // 데이터베이스 오류
        }
    }

    @Override
    public ResponseDto<ResponseUserDto> updateUser(String userId, UpdateUserRequestDto dto) {
        ResponseUserDto data = null;

        // 1. 닉네임 유효성 검사
        if (dto.getNickName() != null && !dto.getNickName().matches("^[a-zA-Z가-힣0-9]{1,10}$")) {
            return ResponseDto.setFailed(ResponseMessage.VALIDATION_FAIL + "nickName");
        }
        // 2. 이름 유효성 검사
        if (dto.getUserName() != null && !dto.getUserName().matches("^[a-zA-Z가-힣]+$")) {
            return ResponseDto.setFailed(ResponseMessage.VALIDATION_FAIL + "userName");
        }

        // 3. email 유효성 검사
        if (dto.getEmail() != null && !dto.getEmail().matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
            return ResponseDto.setFailed(ResponseMessage.VALIDATION_FAIL + "email");
        }

        // 4. 휴대폰번호 유효성 검사
        if (dto.getPhoneNumber() != null && !dto.getPhoneNumber().matches("^01[016789]\\d{7,8}$")) {
            return ResponseDto.setFailed(ResponseMessage.VALIDATION_FAIL + "phoneNumber");
        }

        try {
            // 2. 사용자 조회
            Optional<User> optionalUser = userRepository.findById(userId);
            if (optionalUser.isEmpty()) {
                return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_DATA);
            }

            User user = optionalUser.get();

            // 3. 사용자 권한 확인
            if (!user.getUserId().equals(userId)) {
                return ResponseDto.setFailed(ResponseMessage.NO_PERMISSION);
            }

            // 4. 닉네임 중복 확인
            if (!user.getNickName().equals(dto.getNickName()) &&
                    userRepository.existsByNickName(dto.getNickName())) {
                return ResponseDto.setFailed(ResponseMessage.DUPLICATED_TEL_NICKNAME);
            }

            // 5. 프로필 이미지 처리
            if (dto.getProfileImage() != null) {
                String profileImgPath = imgFileService.convertImgFile(dto.getProfileImage(), "profile");
                user.setProfileImage(profileImgPath);
            }

            // 6. 수정 가능한 필드 업데이트
            user.setUserName(dto.getUserName());
            user.setNickName(dto.getNickName());
            user.setRegion(dto.getRegion());
            user.setPhoneNumber(dto.getPhoneNumber());
            user.setEmail(dto.getEmail());

            // 7. 데이터 저장
            userRepository.save(user);
            data = new ResponseUserDto(user);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }


    // 계정 삭제
    @Override
    @Transactional
    public ResponseDto<Void> deleteUser(String userId, DeleteUserRequestDto dto) {
        String password = dto.getPassword();

        // 비밀번호 유효성 검사
        if (password == null || password.isEmpty()) {
            return ResponseDto.setFailed(ResponseMessage.VALIDATION_FAIL);
        }

        try {
            User user = userRepository.findByUserId(userId)
                    .orElse(null);
            if (user == null) {
                return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_DATA);
            }
            // 비밀번호 검증
            if(!bCryptPasswordEncoder.matches(password, user.getPassword())) {
                return ResponseDto.setFailed(ResponseMessage.NOT_MATCH_PASSWORD);
            }

            userRepository.deleteUser(userId);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, null);
    }

    @Override
    public ResponseDto<Boolean> resetPassword(String userId, UpdateUserPasswordRequestDto dto) {
        String newPassword = dto.getNewPassword();
        // 비밀번호 유효성 검사
        if (newPassword == null || newPassword.isEmpty()
                || !newPassword.matches("^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[\\W_])[a-zA-Z\\d\\W_]{8,16}$")) {
            return ResponseDto.setFailed(ResponseMessage.VALIDATION_FAIL);
        }
        try{
            Optional<User> optionalUser = userRepository.findByUserId(userId);

            if(optionalUser.isEmpty()) {
                return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_DATA);
            }

            // 비밀번호 암호화
            String encodedPassword = bCryptPasswordEncoder.encode(newPassword);

            User user = optionalUser.get();
            user.setPassword(encodedPassword);
            userRepository.save(user);

            return ResponseDto.setSuccess(ResponseMessage.SUCCESS, true);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
    }

    // 닉네임 중복 확인
    @Override
    public ResponseDto<Boolean> duplicationNickName(String nickName) {
        try {
            boolean result = userRepository.existsByNickName(nickName);

            if (result) {
                return ResponseDto.setSuccess(ResponseMessage.DUPLICATED_TEL_NICKNAME, result);
            } else {
                return ResponseDto.setSuccess(ResponseMessage.SUCCESS, result);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
    }

    // 비밀번호 일치여부 확인
    @Override
    public ResponseDto<Boolean> matchPassword(String userId, RequestUserDto dto) {
        String password = dto.getPassword();

        // 비밀번호 유효성 검사
        if (password == null || password.isEmpty()) {
            return ResponseDto.setFailed(ResponseMessage.NO_PERMISSION);
        }

        try {
                Optional<User> optionalUser = userRepository.findByUserId(userId);

                if (optionalUser.isEmpty()) {
                    return ResponseDto.setSuccess(ResponseMessage.NOT_EXIST_DATA, false);
                }

                User user = optionalUser.get();

                boolean isMatch = bCryptPasswordEncoder.matches(password, user.getPassword());

                if (!isMatch) {
                    return ResponseDto.setSuccess(ResponseMessage.NOT_MATCH_PASSWORD, false);
                }

                return ResponseDto.setSuccess(ResponseMessage.SUCCESS, true);

        } catch (Exception e) {
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
    }
}
