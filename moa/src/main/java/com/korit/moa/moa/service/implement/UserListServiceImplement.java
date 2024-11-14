package com.korit.moa.moa.service.implement;

import com.korit.moa.moa.common.constant.ResponseMessage;
import com.korit.moa.moa.dto.ResponseDto;
import com.korit.moa.moa.dto.user_list.response.GroupResponseDto;
import com.korit.moa.moa.repository.UserListRepository;
import com.korit.moa.moa.service.UserListService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
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
}
