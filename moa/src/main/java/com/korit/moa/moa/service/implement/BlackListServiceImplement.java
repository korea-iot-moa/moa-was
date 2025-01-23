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
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BlackListServiceImplement implements BlackListService {

    private final BlackListRepository blackListRepository;
    private final UserRepository userRepository;

    @Override
    public ResponseDto<List<ResponseGetBlackListDto>> getBlackList(Long groupId) {
        List<ResponseGetBlackListDto> data = null;
        try {
            List<Object[]> blackLists = blackListRepository.findByGroup(groupId);

            data = blackLists.stream()
                    .map(result -> {
                        Long blackListId = result[0] != null ? Long.valueOf(result[0].toString()) : null;
                        String userId = result[1] != null ? result[1].toString() : null;
                        String profileImage = result[2] != null ? result[2].toString() : null;
                        String nickName = result[3] != null ? result[3].toString() : null;

                        return new ResponseGetBlackListDto(blackListId, userId,
                                profileImage, nickName);
                    })
                    .distinct()
                    .collect(Collectors.toList());

            return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
    }

    @Override
    @Transactional
    public ResponseDto<ResponseBlackListDto> postBlackList(Long groupId, String userId) {
        ResponseBlackListDto data = null;
        try {
            if (!userRepository.existsByUserId(userId)) {
                return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_USER);
            }
            boolean alreadyExists = blackListRepository.existsByUserIdAndGroupId(userId, groupId);
            if (alreadyExists) {
                return ResponseDto.setFailed(ResponseMessage.DUPLICATED_USER_ID);
            }

            BlackList blackList =  BlackList.builder()
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
    @Transactional
    public ResponseDto<Void> deleteBlackList(Long groupId, String userId) {
        try {
            Optional<BlackList> blackList = blackListRepository.findByGroupIdAndUserId(groupId, userId);
            if (blackList.isEmpty()) {
                return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_DATA);
            }
            blackListRepository.delete(blackList.get());
            return ResponseDto.setSuccess(ResponseMessage.SUCCESS,null);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
    }
}
