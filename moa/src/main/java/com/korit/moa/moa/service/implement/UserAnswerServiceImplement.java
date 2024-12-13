package com.korit.moa.moa.service.implement;

import com.korit.moa.moa.common.constant.ResponseMessage;
import com.korit.moa.moa.dto.ResponseDto;
import com.korit.moa.moa.dto.user_answer.request.RequestUserAnswerDto;
import com.korit.moa.moa.dto.user_answer.response.ResponseUserAnswerDto;
import com.korit.moa.moa.entity.userAnswer.UserAnswer;
import com.korit.moa.moa.repository.UserAnswerRepository;
import com.korit.moa.moa.service.UserAnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class UserAnswerServiceImplement implements UserAnswerService {

    public final UserAnswerRepository userAnswerRepository;

    @Override
    public ResponseDto<ResponseUserAnswerDto> createUserAnswer(String userId, RequestUserAnswerDto dto, Long answerId) {

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
                    .isApproved(false)
                    .build();
            System.out.println(dto);
            userAnswerRepository.save(userAnswers);

            System.out.println(userAnswerRepository);
            ResponseUserAnswerDto data = new ResponseUserAnswerDto(userAnswers);
            return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }

    }
}
