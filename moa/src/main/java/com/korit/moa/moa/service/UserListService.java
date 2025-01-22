package com.korit.moa.moa.service;

import com.korit.moa.moa.dto.ResponseDto;
import com.korit.moa.moa.dto.user_list.request.UserLevelRequestDto;
import com.korit.moa.moa.dto.user_list.response.*;

import java.util.List;

public interface UserListService {

    ResponseDto<List<GroupResponseDto>> getMyGroups(String userId);

    ResponseDto<List<UserListResponseDto>> getUserList(Long groupId);

    ResponseDto<Void> deleteUserList(String userId, Long groupId);

    ResponseDto<UserLevelResponseDto> putUserLevel(Long groupId , UserLevelRequestDto dto);

    ResponseDto<Void> deleteUser(Long groupId, String userId);

    ResponseDto<List<UserGenderRatioResponseDto>> getUserListGender(Long groupId);

    ResponseDto<List<MonthRatioResponseDto>> getMonthUserList(Long groupId);

    ResponseDto<Boolean> duplicateUserId(String userId, Long groupId);
}
