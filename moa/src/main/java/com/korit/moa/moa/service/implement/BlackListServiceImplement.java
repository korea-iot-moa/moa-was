package com.korit.moa.moa.service.implement;

import com.korit.moa.moa.common.constant.ResponseMessage;
import com.korit.moa.moa.dto.ResponseDto;
import com.korit.moa.moa.dto.black_list.response.ResponseBlackListDto;
import com.korit.moa.moa.dto.black_list.response.ResponseGetBlackListDto;
import com.korit.moa.moa.entity.balckList.BlackList;
import com.korit.moa.moa.entity.meetingGroup.MeetingGroup;
import com.korit.moa.moa.entity.user.User;
import com.korit.moa.moa.entity.userList.UserLevel;
import com.korit.moa.moa.entity.userList.UserList;
import com.korit.moa.moa.repository.BlackListRepository;
import com.korit.moa.moa.repository.MeetingGroupRepository;
import com.korit.moa.moa.repository.UserRepository;
import com.korit.moa.moa.service.BlackListService;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BlackListServiceImplement implements BlackListService {

    private final BlackListRepository blackListRepository;
    private final UserRepository userRepository;

    // 블랙 리스트 조회
    @Override
    public ResponseDto<List<ResponseGetBlackListDto>> getBlackList(Long groupId) {
        List<ResponseGetBlackListDto> data = null;
        try {
            List<Object[]> blackLists = blackListRepository.findByGroup(groupId);
            data = blackLists.stream()
                    .map(result -> new ResponseGetBlackListDto(
                            result[0].toString(),
                            result[1].toString(),
                            result[2].toString()
                    ))
                    .collect(Collectors.toList());

            return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
    }


    @Override
    // 블랙 리스트 등록
    public ResponseDto<ResponseBlackListDto> postBlackList(Long groupId, String userId) {
        ResponseBlackListDto data = null;
        System.out.println(userId);
        try {
            if (!userRepository.existsByUserId(userId)) {
                return ResponseDto.setFailed("사용자를 찾을 수 없습니다.");
            }
            System.out.println(userId);
            if (blackListRepository.existsByUserId(userId)) {
                return ResponseDto.setFailed("이미 블랙리스트에 등록된 사용자입니다.");
            }

            BlackList blackList = BlackList.builder()
                    .groupId(groupId)
                    .userId(userId)
                    .build();
            blackListRepository.save(blackList);
            data = new ResponseBlackListDto(blackList);
            return ResponseDto.setSuccess(ResponseMessage.SUCCESS,data);
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
