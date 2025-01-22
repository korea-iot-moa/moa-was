package com.korit.moa.moa.service.implement;

import com.korit.moa.moa.common.constant.ResponseMessage;
import com.korit.moa.moa.dto.ResponseDto;
import com.korit.moa.moa.dto.auth.request.SignInRequestDto;
import com.korit.moa.moa.dto.auth.request.SignUpRequestDto;
import com.korit.moa.moa.dto.auth.response.SignInResponseDto;
import com.korit.moa.moa.dto.auth.response.SignUpResponseDto;
import com.korit.moa.moa.entity.user.*;
import com.korit.moa.moa.provider.JwtProvider;
import com.korit.moa.moa.repository.HobbyRepository;
import com.korit.moa.moa.repository.UserHobbiesRepository;
import com.korit.moa.moa.repository.UserRepository;
import com.korit.moa.moa.service.AuthService;
import com.korit.moa.moa.service.ImgFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthServiceImplement implements AuthService {
    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ImgFileService imgFileService;
    private final UserHobbiesRepository userHobbiesRepository;
    private final HobbyRepository hobbyRepository;

    @Override
    public ResponseDto<SignUpResponseDto> signUp(SignUpRequestDto dto) {

        String userId = dto.getUserId();
        String password = dto.getPassword();
        String confirmPassword = dto.getConfirmPassword();
        String userName = dto.getUserName();
        String nickName = dto.getNickName();
        Gender userGender = dto.getUserGender();
        String userBirthdate = dto.getUserBirthDate();
        Region region = dto.getRegion();
        Set<Long> hobbiesData = dto.getHobbies();
        String joinPath = dto.getJoinPath();
        String snsId = dto.getSnsId();
        String phoneNumber = dto.getPhoneNumber();
        String email = dto.getEmail();

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

        if (phoneNumber == null || phoneNumber.isEmpty() || !phoneNumber.matches("^01[016789]\\d{7,8}$")) {
            return ResponseDto.setFailed(ResponseMessage.VALIDATION_FAIL);
        }

        if (email == null || email.isEmpty() || !email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
            return ResponseDto.setFailed(ResponseMessage.VALIDATION_FAIL);
        }

        // 생년월일 유효성 검사
        if (userBirthdate == null) {
            return ResponseDto.setFailed(ResponseMessage.VALIDATION_FAIL);
        }

        // 취미 유효성 검사
        if (hobbiesData.size() != 0 && hobbiesData.size() != 3) {
            return ResponseDto.setFailed(ResponseMessage.VALIDATION_FAIL);
        }

        if (joinPath == null || joinPath.isEmpty()) {
            return ResponseDto.setFailed(ResponseMessage.VALIDATION_FAIL);
        }



        if (snsId.isEmpty()) {
            return ResponseDto.setFailed(ResponseMessage.VALIDATION_FAIL);
        }


        try {
            String profileImgPath = null;
            if(dto.getProfileImage() != null) {
                profileImgPath = imgFileService.convertImgFile(dto.getProfileImage(), "profile");
            }
            DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
            String encodingPassword = bCryptPasswordEncoder.encode(password);
            User user = User.builder()
                    .userId(userId)
                    .password(encodingPassword)
                    .userBirthDate(dateFormat.parse(dto.getUserBirthDate()))
                    .userGender(userGender)
                    .userName(userName)
                    .nickName(nickName)
                    .profileImage(profileImgPath)
                    .region(region)
                    .joinPath(joinPath)
                    .snsId(snsId)
                    .phoneNumber(phoneNumber)
                    .email(email)
                    .build();
            User saveUser = userRepository.save(user);

            Set<UserHobbies> hobbies = dto.getHobbies().stream().map(hobbyId ->
                    UserHobbies.builder()
                            .user(saveUser)
                            .hobby(hobbyRepository.findById(hobbyId).get())
                            .build()
            ).collect(Collectors.toSet());
            saveUser.setHobbies(Set.copyOf(userHobbiesRepository.saveAll(hobbies)));
            SignUpResponseDto data = new SignUpResponseDto(saveUser);
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
            String token = jwtProvider.generateJwtToken(userId, user.getNickName(), user.getProfileImage());
            int exprTime = jwtProvider.getExpiration();

            data = new SignInResponseDto(user, token, exprTime);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    // 회원가입 시 취미 렌더링
    @Override
    public ResponseDto<List<Hobby>> getHobbies() {
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, hobbyRepository.findAll());
    }

    // 회원가입 아이디 중복 확인
    @Override
    public ResponseDto<Boolean> duplicateId(String userId) {
        try {
            boolean result = userRepository.existsByUserId(userId);

            if (result == true) {
                return ResponseDto.setSuccess(ResponseMessage.DUPLICATED_USER_ID, result);
            }else {
                return ResponseDto.setSuccess(ResponseMessage.SUCCESS, result);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
    }

    // 회원가입 닉네임 중복확인
    @Override
    public ResponseDto<Boolean> duplicateNickName(String nickName) {
        try {
            boolean result = userRepository.existsByNickName(nickName);

            if (result == true) {
                return ResponseDto.setSuccess(ResponseMessage.DUPLICATED_USER_ID, result);
            }else {
                return ResponseDto.setSuccess(ResponseMessage.SUCCESS, result);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
    }

}