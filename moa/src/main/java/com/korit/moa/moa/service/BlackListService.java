package com.korit.moa.moa.service;


import com.korit.moa.moa.dto.ResponseDto;
import com.korit.moa.moa.dto.black_list.response.ResponseBlackListDto;
import com.korit.moa.moa.dto.black_list.response.ResponseGetBlackListDto;


import java.util.List;

public interface BlackListService {

    //블래리스트 조회
    ResponseDto<List<ResponseGetBlackListDto>> getBlackList(Long groupId);

    //블랙 리스트 등록 및 유저 리스트에 삭제
    ResponseDto<ResponseBlackListDto> postBlackList(Long groupId,String userId);

    //블랙 리스트 삭제
    ResponseDto<Void> deleteBlackList(Long blackListId);


}
