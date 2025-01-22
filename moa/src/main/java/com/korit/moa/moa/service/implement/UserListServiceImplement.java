package com.korit.moa.moa.service.implement;

import com.korit.moa.moa.common.constant.ResponseMessage;
import com.korit.moa.moa.dto.ResponseDto;
import com.korit.moa.moa.dto.user_list.request.UserLevelRequestDto;
import com.korit.moa.moa.dto.user_list.response.*;
import com.korit.moa.moa.entity.user.User;
import com.korit.moa.moa.entity.userList.UserLevel;
import com.korit.moa.moa.entity.userList.UserList;
import com.korit.moa.moa.repository.UserListRepository;
import com.korit.moa.moa.repository.UserRepository;
import com.korit.moa.moa.service.UserListService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserListServiceImplement implements UserListService {

    private final UserListRepository userListRepository;
    private  final UserRepository userRepository;

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

    @Transactional
    @Override
    public ResponseDto<Void> deleteUserList(String userId, Long groupId) {
        try{
            userListRepository.deleteUserList(userId, groupId);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, null);
    }

    @Override
    public ResponseDto<UserLevelResponseDto> putUserLevel(Long groupId, UserLevelRequestDto dto) {
        UserLevelResponseDto data = null;
        UserLevel userLevel = dto.getUserLevel();
        String userId =  dto.getUserId();
        if(userLevel .equals("관리자")) {
            return ResponseDto.setFailed(ResponseMessage.NO_PERMISSION);
        }
        try{
            UserList userList = userListRepository.findByGroupIdAndUserId(groupId,userId)
                    .orElseThrow(() -> new IllegalArgumentException(
                            "유저리스트를 찾을 수 없습니다. groupId: " + groupId + ", userId: " + userId)
                    );
            userList.setUserLevel(userLevel);

            userListRepository.save(userList);
            data = new UserLevelResponseDto(userList);

            return ResponseDto.setSuccess(ResponseMessage.SUCCESS,data);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }

    }

    @Override
    public ResponseDto<Void> deleteUser(Long groupId, String userId) {
        try{

            Optional<User> userOptional = userRepository.findByUserId(userId);
           if(userOptional.isPresent()){
               userListRepository.deleteByUserIdAndGroupId(userOptional.get().getUserId(),groupId);
           }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
       return ResponseDto.setSuccess(ResponseMessage.SUCCESS,null);
    }

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
                return ResponseDto.setSuccess(ResponseMessage.SUCCESS, false);
            }

            UserList userList = userListOptional.get();
            userListRepository.save(userList);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, true);
    }

}
