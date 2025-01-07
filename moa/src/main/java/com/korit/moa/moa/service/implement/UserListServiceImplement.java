package com.korit.moa.moa.service.implement;

import com.korit.moa.moa.common.constant.ResponseMessage;
import com.korit.moa.moa.dto.ResponseDto;
import com.korit.moa.moa.dto.user_answer.response.ResponseGetUserAnswer;
import com.korit.moa.moa.dto.user_answer.response.ResponseUserAnswerDto;
import com.korit.moa.moa.dto.user_list.request.UserLevelRequestDto;
import com.korit.moa.moa.dto.user_list.response.*;
import com.korit.moa.moa.entity.user.Gender;
import com.korit.moa.moa.entity.user.User;
import com.korit.moa.moa.entity.userList.UserLevel;
import com.korit.moa.moa.entity.userList.UserList;
import com.korit.moa.moa.entity.userList.UserListId;
import com.korit.moa.moa.repository.UserListRepository;
import com.korit.moa.moa.repository.UserRepository;
import com.korit.moa.moa.service.UserListService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.SignatureUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
//@Transactional
public class UserListServiceImplement implements UserListService {

    private final UserListRepository userListRepository;
    private  final UserRepository userRepository;

    // 내가 가입한 모임 조회
    @Override
    public ResponseDto<List<GroupResponseDto>> getMyGroups(String userId) {
        List<GroupResponseDto> data = null;

        try{
            List<Object[]> results = userListRepository.findGroupByUserId(userId);
            data = results.stream()
                    .map(result -> new GroupResponseDto((Long) result[0], (String) result[2], (String) result[1]))
                    .collect(Collectors.toList());

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    // 모임 내 유저리스트 조회
    @Override
    public ResponseDto<List<UserListResponseDto>> getUserList(Long groupId) {
        List<UserListResponseDto> data = null;

        try{
            List<Object[]> results = userListRepository.findUsersByGroupId(groupId);
            data = results.stream()
                    .map(result -> {
                        User user = (User) result[0];
                        UserList userList = (UserList) result[1];
                        return  new UserListResponseDto(
                                user.getUserId(), user.getNickName(), user.getProfileImage(),
                                userList.getUserLevel());
                    })
                    .collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    // 모임 나가기
    @Transactional
    @Override
    public ResponseDto<Void> deleteUserList(String userId, Long groupId) {
        try{
//            if (!userListRepository.existsByUserIdAndGroupId(userId, groupId)) {
//                return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_DATA);
//            }
            userListRepository.deleteUserList(userId, groupId);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, null);
    }

    //유저 등급 수정
    @Override
    public ResponseDto<UserLevelResponseDto> putUserLevel(Long groupId, UserLevelRequestDto dto) {
        UserLevelResponseDto data = null;
        UserLevel userLevel = dto.getUserLevel();
        try{
            UserList userList = userListRepository.findByGroupId(groupId)
                 .orElseThrow(() -> new IllegalArgumentException("유저리스트를 찾을 수 없습니다" + groupId));
            userList.setUserLevel(userLevel);

            userListRepository.save(userList);
            data = new UserLevelResponseDto(userList);

            return ResponseDto.setSuccess(ResponseMessage.SUCCESS,data);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }

    }

    //유저 추방
    @Override
    @Transactional
    public ResponseDto<Void> deleteUser(Long groupId, String userId) {
        try{
            Optional<User> userOptional = userRepository.findByUserId(userId);
           if(userOptional.isPresent()){
               userListRepository.deleteByUserId(userOptional.get().getUserId());
           }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
       return ResponseDto.setSuccess(ResponseMessage.SUCCESS,null);
    }

    //모임 내 남여 성비 차트
    @Override
    public ResponseDto<List<UserGenderRatioResponseDto>> getUserListGender(Long groupId) {
        List<UserGenderRatioResponseDto> data = new ArrayList<>();
        try {
            List<Object[]> results = userListRepository.findGroupByUserGenderWithCount(groupId);
            int total = results.stream().mapToInt(result -> ((Long) result[1]).intValue()).sum();
            data = results.stream()
                    .map(result -> new UserGenderRatioResponseDto
                            (result[0].toString(), (Long) result[1], (double) ((Long) result[1]) / total * 100))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    //모임 분기별 유입율 차트
    @Override
    public ResponseDto<List<MonthRatioResponseDto>> getMonthUserList(Long groupId) {
        List<MonthRatioResponseDto> data = new ArrayList<>();
        try{
            List<Object[]> results = userListRepository.getQuarterlyData(groupId);
            data = results.stream()
                    .map(result -> {
                        String[] parts = result[0].toString().split("-");
                        if (parts.length != 2) {
                            throw new IllegalArgumentException("Invalid date format: " + result[0]);
                        }
                        int year = Integer.parseInt(parts[0]);
                        int quarter = Integer.parseInt(parts[1]);
                        long userCount = Long.parseLong(result[1].toString());
                        double ratio = Double.parseDouble(result[2].toString());

                        return new MonthRatioResponseDto(quarter, userCount, ratio);

                        }
                    )
                    .collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS,data);
    }

    @Override
    public ResponseDto<Boolean> duplicateUserId(String userId, Long groupId) {
        try{
            Optional<UserList> userListOptional = userListRepository.findByUserIdAndGroupId(userId, groupId);

            if (userListOptional.isEmpty()) {
                return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_DATA);
            }

            UserList userList = userListOptional.get();
            userListRepository.save(userList);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, true);
    }

    // 그룹 아이디 유저리스트에 유저 존재여부
//    @Override
//    public ResponseDto<ResponseGetUserIdDto> getUserIdUserList(String userId, Long groupId) {
//        ResponseGetUserIdDto data = null;
//
//        try{
//            Optional<UserList> userListOptional = userListRepository.findByUserIdAndGroupId(userId, groupId);
//            if (userListOptional.isEmpty()) {
//                return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_DATA);
//            }
//
//            UserList userList = userListOptional.get();
//            data = new ResponseGetUserIdDto(userList);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
//        }
//        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
//    }



}
