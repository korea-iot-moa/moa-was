package com.korit.moa.moa.service;

import com.korit.moa.moa.dto.ResponseDto;
import com.korit.moa.moa.dto.user_list.response.GroupResponseDto;
import com.korit.moa.moa.dto.user_list.response.UserListResponseDto;

import java.util.List;

public interface UserListService {
    ResponseDto<List<GroupResponseDto>> getMyGroups(String userId);
    ResponseDto<List<UserListResponseDto>> getUserList(Long groupId);
    ResponseDto<Void> deleteUserList(String userId, Long groupId);
}
