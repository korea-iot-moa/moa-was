package com.korit.moa.moa.service;

import com.korit.moa.moa.dto.ResponseDto;
import com.korit.moa.moa.dto.group.response.ResponseGroupDto;

public interface MeetingGroupService {

    ResponseDto<ResponseGroupDto> findGroupHomeSelectByGroupId(Long userId);
}
