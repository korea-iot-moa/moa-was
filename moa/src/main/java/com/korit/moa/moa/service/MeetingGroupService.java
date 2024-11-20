package com.korit.moa.moa.service;

import com.korit.moa.moa.dto.ResponseDto;
import com.korit.moa.moa.dto.group.response.ResponseGroupDto;
import com.korit.moa.moa.dto.group.response.SearchResponseDto;

import java.util.List;

public interface MeetingGroupService {



    ResponseDto<List<ResponseGroupDto>> findGroupHomeSelectByUserId(Long userId);

    ResponseDto<List<SearchResponseDto>> findGroupSearchByGroupTitle(String groupTitle);
}
