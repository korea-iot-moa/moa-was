package com.korit.moa.moa.service.implement;

import com.korit.moa.moa.common.constant.ResponseMessage;
import com.korit.moa.moa.dto.ResponseDto;
import com.korit.moa.moa.dto.user_answer.request.RequestDeleteUserAnswerDto;
import com.korit.moa.moa.dto.user_answer.request.RequestUserAnswerDto;
import com.korit.moa.moa.dto.user_answer.response.ResponseUserAnswerDto;
import com.korit.moa.moa.entity.meetingGroup.MeetingGroup;
import com.korit.moa.moa.entity.user.User;
import com.korit.moa.moa.entity.userAnswer.UserAnswer;
import com.korit.moa.moa.entity.userList.UserLevel;
import com.korit.moa.moa.entity.userList.UserList;
import com.korit.moa.moa.repository.MeetingGroupRepository;
import com.korit.moa.moa.repository.UserAnswerRepository;
import com.korit.moa.moa.repository.UserListRepository;
import com.korit.moa.moa.repository.UserRepository;
import com.korit.moa.moa.service.UserAnswerService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserAnswerServiceImplement implements UserAnswerService {
    private final UserRepository userRepository;
    private final UserListRepository userListRepository;
    private final UserAnswerRepository userAnswerRepository;
    private final MeetingGroupRepository meetingGroupRepository;


    @Override
    public ResponseDto<ResponseUserAnswerDto> postMeetingGroup(Long groupId, RequestUserAnswerDto dto) {
        String userId = dto.getUserId();
        String userAnswer = dto.getUserAnswer();
        if (userAnswer == null || userAnswer.isEmpty() ||
                !userAnswer.matches("t^(?=.*[!@#$%^&*(),.?\":{}|<>])[A-Za-z\\d!@#$%^&*(),.?\":{}|<>] {10,200}$"))
        {
            return ResponseDto.setFailed(ResponseMessage.DUPLICATED_USER_ID);
        }
        try {
              userAnswerRepository.findById(groupId);
              UserAnswer newUserAnswer = UserAnswer.builder()
                      .userId(userId)
                      .userAnswer(userAnswer)
                      .build();

              userAnswerRepository.save(newUserAnswer);
              ResponseUserAnswerDto data = new ResponseUserAnswerDto(newUserAnswer);
            return ResponseDto.setSuccess(ResponseMessage.SUCCESS,data);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
    }
    //참여 요청 조회
    @Override
    public ResponseDto<List<ResponseUserAnswerDto>> getUserAnswer(Long groupId) {
        List<ResponseUserAnswerDto> data = null;
        try{
            List<UserAnswer> userAnswers =  userAnswerRepository.findByGroupId(groupId);

            data = userAnswers.stream()
                    .map(ResponseUserAnswerDto:: new)
                    .collect(Collectors.toList());

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS,data);

    }

    //참여 승인 및 거절
    @Override
    @Transactional(rollbackOn = Exception.class)
    public ResponseDto<Void> approveUserAnswer(Long groupId, RequestDeleteUserAnswerDto dto) {
        int isApproved = dto.getIsApproved();
        try{
            Optional<UserAnswer> optionalUserAnswer = userAnswerRepository.findById(groupId);
            if (optionalUserAnswer.isEmpty()) {
                return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_GROUP);
            }
                if(isApproved == 1){
                    MeetingGroup meetingGroup = meetingGroupRepository.findById(groupId).get();
                    User user = userRepository.findByUserId(dto.getUserId()).get();
                    UserList userList = userListRepository.save(UserList.builder()
                            .group(meetingGroup)
                            .user(user)
                            .userLevel(UserLevel.일반회원)
                            .joinDate(new Date())
                            .build());

                    userAnswerRepository.deleteByUserId(dto.getUserId());
                }
                if (isApproved == 0){
                    userAnswerRepository.deleteByUserId(dto.getUserId());
                    return ResponseDto.setSuccess(ResponseMessage.SUCCESS,null);
                }

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS,null);
    }

}
