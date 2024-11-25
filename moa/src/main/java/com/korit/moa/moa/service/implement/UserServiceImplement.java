package com.korit.moa.moa.service.implement;

import com.korit.moa.moa.common.constant.ResponseMessage;
import com.korit.moa.moa.dto.ResponseDto;
import com.korit.moa.moa.dto.user.request.DeleteUserRequestDto;
import com.korit.moa.moa.dto.user.request.UpdateUserRequestDto;
import com.korit.moa.moa.dto.user.response.ResponseUserDto;
import com.korit.moa.moa.entity.user.User;
import com.korit.moa.moa.repository.UserRepository;
import com.korit.moa.moa.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImplement implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public ResponseDto<ResponseUserDto> findByUserId(String userId) {
        ResponseUserDto data = null;

        try{
            Optional<User> optionalUser = userRepository.findByUserId(userId);

            User user = optionalUser.get();
            data = new ResponseUserDto(user);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    @Override
    public ResponseDto<ResponseUserDto> updateByUserId(String userId, UpdateUserRequestDto dto) {
        ResponseUserDto data = null;

        try {
            Optional<User> optionalUser = userRepository.findById(userId);
            if(optionalUser.isEmpty()){
                return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_DATA);
            }

            User user = optionalUser.get();

            if(!user.getUserId().equals(userId)) {
                return ResponseDto.setFailed(ResponseMessage.NO_PERMISSION);
            }

            //3. 넥네임 중복 확인
            if(!user.getNickName().equals(dto.getNickName()) &&
                    userRepository.existsByNickName(dto.getNickName())) {
                return ResponseDto.setFailed(ResponseMessage.DUPLICATED_TEL_NICKNAME);
            }

            user = User.builder()
                    .userId(user.getUserId())
                    .password(user.getPassword())
                    .userBirthDate(user.getUserBirthDate())
                    .userGender((user.getUserGender()))
                    .userName(dto.getUserName())
                    .nickName(dto.getNickName())
                    .hobbies(dto.getHobbies())
                    .profileImage(dto.getProfileImage())
                    .region(dto.getRegion())
                    .build();

            userRepository.save(user);
            data = new ResponseUserDto(user);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);

    }

    @Override
    public ResponseDto<Void> deleteUser(DeleteUserRequestDto dto) {
        String userId = dto.getUserId();
        String password = dto.getPassword();

        // 비밀번호 유효성 검사
        if (password == null || password.isEmpty()) {
            return ResponseDto.setFailed(ResponseMessage.NO_PERMISSION);
        }
        // 아이디 유효성 검사
        if (userId == null || userId.isEmpty()) {
            return ResponseDto.setFailed(ResponseMessage.NO_PERMISSION);
        }

        try {
            User user = userRepository.findByUserId(userId)
                    .orElse(null);
            if (user == null) {
                return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_DATA);
            }

            if(!bCryptPasswordEncoder.matches(password, user.getPassword())) {
                return ResponseDto.setFailed(ResponseMessage.NOT_MATCH_PASSWORD);
            }

            userRepository.deleteById(userId);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, null);
    }


}
