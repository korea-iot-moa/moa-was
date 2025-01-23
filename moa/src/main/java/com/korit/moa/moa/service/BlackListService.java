package com.korit.moa.moa.service;

import com.korit.moa.moa.dto.ResponseDto;
import com.korit.moa.moa.dto.black_list.response.ResponseBlackListDto;
import com.korit.moa.moa.dto.black_list.response.ResponseGetBlackListDto;


import java.util.List;

public interface BlackListService {

    ResponseDto<List<ResponseGetBlackListDto>> getBlackList(Long groupId);

    ResponseDto<ResponseBlackListDto> postBlackList(Long groupId,String userId);

    ResponseDto<Void> deleteBlackList(Long groupId , String userId);

}
