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
import com.korit.moa.moa.entity.userList.UserListId;
import com.korit.moa.moa.repository.MeetingGroupRepository;
import com.korit.moa.moa.repository.UserAnswerRepository;
import com.korit.moa.moa.repository.UserListRepository;
import com.korit.moa.moa.repository.UserRepository;
import com.korit.moa.moa.service.UserAnswerService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
        LocalDate date = LocalDate.now();
//        if (userAnswer == null || userAnswer.isEmpty()
//              !userAnswer.matches("^(?!\\s)[가-힣A-Za-z\\s]{9,199}[가-힣A-Za-z\\s!@#$%^&*(),.?\":{}|<>]$\n")
//        ) {
//            return ResponseDto.setFailed(ResponseMessage.VALIDATION_FAIL);
//        }
        try {
            userAnswerRepository.findById(groupId);
            UserAnswer newUserAnswer = UserAnswer.builder()
                    .userId(userId)
                    .groupId(groupId)
                    .answerDate(date)
                    .isApproved(2)
                    .userAnswer(userAnswer)
                    .build();

            userAnswerRepository.save(newUserAnswer);
            ResponseUserAnswerDto data = new ResponseUserAnswerDto(newUserAnswer);
            return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
    }

    //참여 요청 조회
    @Override
    public ResponseDto<List<ResponseUserAnswerDto>> getUserAnswer(Long groupId) {
        List<ResponseUserAnswerDto> data = null;
        try {
            List<UserAnswer> userAnswers = userAnswerRepository.findAllByGroupId(groupId);

            data = userAnswers.stream()
                    .map(ResponseUserAnswerDto::new)
                    .collect(Collectors.toList());

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);

    }

    //참여 승인
    @Override
    @Transactional(rollbackOn = Exception.class)
    public ResponseDto<Void> approveUserAnswer(Long groupId, RequestDeleteUserAnswerDto dto) {
        int isApproved = dto.getIsApproved();
        try {
            List<UserAnswer> userAnswers = userAnswerRepository.findAllByGroupId(groupId);

            if (userAnswers.isEmpty()) {
                return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_GROUP);
            }
            if (isApproved == 1) {
                Optional<MeetingGroup> meetingGroupOptional = meetingGroupRepository.findById(groupId);
                if (meetingGroupOptional.isEmpty()) {
                    return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_GROUP);
                }
                MeetingGroup meetingGroup = meetingGroupOptional.get();

                Optional<User> userOptional = userRepository.findByUserId(dto.getUserId());
                if (userOptional.isEmpty()) {
                    return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_USER);
                }
                User user = userOptional.get();

                UserListId userListId = new UserListId(groupId, user.getUserId());
                UserList userList = UserList.builder()
                        .id(userListId)
                        .group(meetingGroup)
                        .user(user)
                        .userLevel(UserLevel.일반회원)
                        .joinDate(new Date())
                        .build();
                userListRepository.save(userList);

                userAnswerRepository.deleteByUserId(dto.getUserId());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, null);
    }

    //참여 요청 거절
    @Override
    public ResponseDto<Void> deleteUserAnswer(Long groupId, RequestDeleteUserAnswerDto dto) {
        int isApproved = dto.getIsApproved();
        try {
            if (isApproved == 0) {
                userAnswerRepository.deleteByUserId(dto.getUserId());
            }
            return ResponseDto.setSuccess(ResponseMessage.SUCCESS, null);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }

    }

    //    모임참여 답변
//    @Override
//    public ResponseDto<ResponseUserAnswerDto> createUserAnswer(String userId, RequestUserAnswerDto dto, Long answerId) {
//
//        Long groupId = dto.getGroupId();
//        String userAnswer = dto.getUserAnswer();
//
//
//        if(userId == null) {
//            return ResponseDto.setFailed(ResponseMessage.VALIDATION_FAIL + "userId");
//        }
//
//        if (userAnswer == null || userAnswer.isEmpty()) {
//            return ResponseDto.setFailed(ResponseMessage.VALIDATION_FAIL + "userAnswer");
//        }
//
//        boolean exists = userAnswerRepository.existsByGroupIdAndUserId(groupId, userId);
//        if (exists) {
//            return ResponseDto.setFailed(ResponseMessage.DUPLICATED_USER_ID);
//        }
//
//        try {
//            UserAnswer userAnswers = UserAnswer.builder()
//                    .answerId(answerId)
//                    .groupId(groupId)
//                    .userId(userId)
//                    .userAnswer(userAnswer)
//                    .answerDate(LocalDate.now())
//                    .isApproved(false)
//                    .build();
//            System.out.println(dto);
//            userAnswerRepository.save(userAnswers);
//
//            System.out.println(userAnswerRepository);
//            ResponseUserAnswerDto data = new ResponseUserAnswerDto(userAnswers);
//            return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
//        }
//
//    }

}

