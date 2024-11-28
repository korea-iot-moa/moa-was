package com.korit.moa.moa.service.implement;

import com.korit.moa.moa.common.constant.ResponseMessage;
import com.korit.moa.moa.dto.ResponseDto;
import com.korit.moa.moa.dto.black_list.response.ResponseBlackListDto;
import com.korit.moa.moa.entity.balckList.BlackList;
import com.korit.moa.moa.repository.BlackListRepository;
import com.korit.moa.moa.service.BlackListService;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BlackListServiceImplement implements BlackListService {

    public final BlackListRepository blackListRepository;


    @Override
    // 블랙 리스트 조회
    public ResponseDto<List<ResponseBlackListDto>> getBlackList(String userId, Long groupId ) {
        List<ResponseBlackListDto> data = null;

        if(groupId == null){
            return ResponseDto.setFailed(ResponseMessage.VALIDATION_FAIL);
        }
        if(userId == null || userId.isEmpty()){
            return ResponseDto.setFailed(ResponseMessage.VALIDATION_FAIL);
        }

        try{
                List<Object[]> results =  blackListRepository.findBlackListByGroupId(groupId);
                data = results.stream()
                        .map(ressult -> {
                            BlackList blackList = (BlackList)ressult[0];
                            return new ResponseBlackListDto(blackList);
                        })
                        .toList();

        }catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    @Override
// 블랙 리스트 등록
    public ResponseDto<ResponseBlackListDto> postBlackList(Long groupId, String userId) {
        ResponseBlackListDto data = null;


        if (groupId == null) {
            return ResponseDto.setFailed(ResponseMessage.VALIDATION_FAIL);
        }
        if (userId == null || userId.isEmpty()) {
            return ResponseDto.setFailed(ResponseMessage.VALIDATION_FAIL );
        }

        try {
            
            BlackList newBlackList = new BlackList();
            newBlackList.setUserId(userId);
            newBlackList.setGroupId(groupId);


            BlackList savedBlackList = blackListRepository.save(newBlackList);
            data = new ResponseBlackListDto(savedBlackList.getBlackListId(),
                    savedBlackList.getUserId(), savedBlackList.getGroupId());

            return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
    }

    @Override
    //블랙 리스트 삭제
    public ResponseDto<Void> deleteBlackList(Long blackListId) {

        try {
            Optional<BlackList> optionalBlackList = blackListRepository.findById(blackListId);
            if(optionalBlackList.isPresent()){
                blackListRepository.deleteById(blackListId);
            }
            return ResponseDto.setSuccess(ResponseMessage.SUCCESS,null);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
    }
}
