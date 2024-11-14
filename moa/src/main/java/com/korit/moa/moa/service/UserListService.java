package com.korit.moa.moa.service;

import com.korit.moa.moa.dto.ResponseDto;
import com.korit.moa.moa.dto.user_list.response.GroupResponseDto;

import java.util.List;

public interface UserListService {
    ResponseDto<List<GroupResponseDto>> getMyGroups(String userId);
}
