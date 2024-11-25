package com.korit.moa.moa.service;

import com.korit.moa.moa.dto.ResponseDto;
import com.korit.moa.moa.dto.group.response.ResponseGroupDto;
import com.korit.moa.moa.dto.group.response.SearchResponseDto;

import java.util.List;

public interface meetingGroupService {

//    ResponseDto<List<ResponseGroupDto>> findHomeSelectByUserId(String userId);

    ResponseDto<List<SearchResponseDto>> findByGroupTitle(String groupTitle);
}
