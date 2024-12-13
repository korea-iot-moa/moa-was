package com.korit.moa.moa.service;

import com.korit.moa.moa.dto.ResponseDto;
import com.korit.moa.moa.dto.user_answer.response.ResponseUserAnswerDto;
import com.korit.moa.moa.dto.user_list.request.UserLevelRequestDto;
import com.korit.moa.moa.dto.user_list.response.*;
import com.korit.moa.moa.entity.userList.UserListId;

import java.util.Date;
import java.util.List;

public interface UserListService {
    ResponseDto<List<GroupResponseDto>> getMyGroups(String userId);
    ResponseDto<List<UserListResponseDto>> getUserList(Long groupId);
    ResponseDto<Void> deleteUserList(String userId, Long groupId);
    ResponseDto<UserLevelResponseDto> putUserLevel(String userId, UserLevelRequestDto dto);
    ResponseDto<Void> deleteUser(UserListId userListId);
    ResponseDto<List<UserGenderRatioResponseDto>> getUserListGender(Long groupId);
    ResponseDto<List<MonthRatioResponseDto>> getMonthUserList(Long groupId);
}
