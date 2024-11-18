package com.korit.moa.moa.service.implement;

import com.korit.moa.moa.common.constant.ResponseMessage;
import com.korit.moa.moa.dto.ResponseDto;
import com.korit.moa.moa.dto.user_list.response.GroupResponseDto;
import com.korit.moa.moa.dto.user_list.response.UserListResponseDto;
import com.korit.moa.moa.entity.user.User;
import com.korit.moa.moa.repository.UserListRepository;
import com.korit.moa.moa.service.UserListService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class UserListServiceImplement implements UserListService {

    private final UserListRepository userListRepository;

    // 내가 가입한 모임 조회
    @Override
    public ResponseDto<List<GroupResponseDto>> getMyGroups(String userId) {
        List<GroupResponseDto> data = null;

        try{
            List<Object[]> results = userListRepository.findGroupByUserId(userId);
            data = results.stream()
                    .map(result -> new GroupResponseDto((Long) result[0], (String) result[1], (String) result[2]))
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
                        return  new UserListResponseDto(user.getUserId(), user.getNickName(), user.getProfileImage());
                    })
                    .collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    // 모임 나가기
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
}
