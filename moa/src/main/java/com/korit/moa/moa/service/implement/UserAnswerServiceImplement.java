package com.korit.moa.moa.service.implement;

import com.korit.moa.moa.common.constant.ResponseMessage;
import com.korit.moa.moa.dto.ResponseDto;
import com.korit.moa.moa.dto.user_answer.request.RequestDeleteUserAnswerDto;
import com.korit.moa.moa.dto.user_answer.request.RequestUserAnswerDto;
import com.korit.moa.moa.dto.user_answer.request.UserAnswerRequestDto;
import com.korit.moa.moa.dto.user_answer.response.ParticipationStatusResponseDto;
import com.korit.moa.moa.dto.user_answer.response.ResponseUserAnswerDto;
import com.korit.moa.moa.dto.user_answer.response.UserAnswerGetReponseDto;
import com.korit.moa.moa.entity.meetingGroup.GroupCategory;
import com.korit.moa.moa.entity.meetingGroup.GroupTypeCategory;
import com.korit.moa.moa.entity.meetingGroup.MeetingGroup;
import com.korit.moa.moa.entity.meetingGroup.MeetingTypeCategory;
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

    //참여 요청 조회
    @Override
    public ResponseDto<List<UserAnswerGetReponseDto>> getUserAnswer(Long groupId) {
        if (groupId == null) {
            return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_GROUP);
        }

        try {
            List<Object[]> userAnswers = userAnswerRepository.findByGroupIdWithTitle(groupId);

            // Object[] 데이터를 DTO로 변환
            List<UserAnswerGetReponseDto> data = userAnswers.stream()
                    .map(UserAnswerGetReponseDto::new)
                    .collect(Collectors.toList());

            return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
    }


    //참여 승인
    @Override
    @Transactional(rollbackOn = Exception.class)
    public ResponseDto<Void> approveUserAnswer(Long groupId, RequestDeleteUserAnswerDto dto) {
        int isApproved = dto.getIsApproved();
        UserAnswer updateData = null;
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
                        .nickName(user.getNickName())
                        .profileImage(user.getProfileImage())
                        .userLevel(UserLevel.일반회원)
                        .joinDate(new Date())
                        .build();
                userListRepository.save(userList);

                updateData = userAnswerRepository.findByGroupIdAndUserId(groupId, dto.getUserId());
                updateData.setIsApproved(1);
                userAnswerRepository.save(updateData);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, null);
    }

    //참여 요청 거절
    @Override
    public ResponseDto<Boolean> refuseRequestUserAnswer(Long groupId, RequestDeleteUserAnswerDto dto) {
        int isApproved = dto.getIsApproved();
        UserAnswer updateData = null;
        try {
            if (isApproved == 0) {
                updateData = userAnswerRepository.findByGroupIdAndUserId(groupId, dto.getUserId());
                updateData.setIsApproved(0);
                userAnswerRepository.save(updateData);
            }
            return ResponseDto.setSuccess(ResponseMessage.SUCCESS, true);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }

    }

    @Override
    public ResponseDto<ResponseUserAnswerDto> createUserAnswer(String userId, UserAnswerRequestDto dto, Long answerId) {

        Long groupId = dto.getGroupId();
        String userAnswer = dto.getUserAnswer();


        if(userId == null) {
            return ResponseDto.setFailed(ResponseMessage.VALIDATION_FAIL + "userId");
        }

        if (userAnswer == null || userAnswer.isEmpty()) {
            return ResponseDto.setFailed(ResponseMessage.VALIDATION_FAIL + "userAnswer");
        }

        boolean exists = userAnswerRepository.existsByGroupIdAndUserId(groupId, userId);
        if (exists) {
            return ResponseDto.setFailed(ResponseMessage.DUPLICATED_USER_ID);
        }

        try {
            UserAnswer userAnswers = UserAnswer.builder()
                    .answerId(answerId)
                    .groupId(groupId)
                    .userId(userId)
                    .userAnswer(userAnswer)
                    .answerDate(LocalDate.now())
                    .isApproved(2)
                    .build();
            userAnswerRepository.save(userAnswers);

            ResponseUserAnswerDto data = new ResponseUserAnswerDto(userAnswers);
            return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }

    }

    @Override
    public ResponseDto<Boolean> duplicateUserAnswer(String userId, Long groupId) {
        try{
            boolean result = userAnswerRepository.existsByGroupIdAndUserId(groupId, userId);

            if (result) {
                return ResponseDto.setSuccess(ResponseMessage.DUPLICATED_TEL_USERANSWER, true);
            }else {
                return ResponseDto.setSuccess(ResponseMessage.SUCCESS, false);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
    }

    @Override
    public ResponseDto<List<ParticipationStatusResponseDto>> findParticipationStatus(String userId) {
        List<ParticipationStatusResponseDto> data = null;

        try {
            List<Object[]> results = userAnswerRepository.findParticipationStatus(userId);
            data = results.stream()
                    .map(result -> {
                        Long groupId = (Long) result[0];
                        String groupTitle = (String) result[1];
                        GroupTypeCategory groupType = (GroupTypeCategory) result[2];
                        MeetingTypeCategory meetingType = (MeetingTypeCategory) result[3];
                        GroupCategory groupCategory = (GroupCategory) result[4];
                        String groupImage = (String) result[5];
                        Long answerId = (Long) result[6];
                        LocalDate answerDate = (LocalDate) result[7];
                        int isApproved = (int) result[8];

                        return new ParticipationStatusResponseDto(
                                groupId,
                                groupTitle,
                                groupType,
                                meetingType,
                                groupCategory,
                                groupImage,
                                answerId,
                                answerDate,isApproved
                        );
                    })
                    .toList();
            return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
    }

    @Override
    public ResponseDto<Boolean> deleteAnswer(Long answerId) {
        try {
            userAnswerRepository.deleteById(answerId);
            return ResponseDto.setSuccess(ResponseMessage.SUCCESS, true);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
    }
}