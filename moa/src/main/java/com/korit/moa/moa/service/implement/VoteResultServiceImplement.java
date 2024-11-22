package com.korit.moa.moa.service.implement;

import com.korit.moa.moa.common.constant.ResponseMessage;
import com.korit.moa.moa.dto.ResponseDto;
import com.korit.moa.moa.dto.vote_result.request.VoteResultRequestDto;
import com.korit.moa.moa.dto.vote_result.response.VoteResultResponseDto;
import com.korit.moa.moa.entity.voteResult.VoteAnswer;
import com.korit.moa.moa.entity.voteResult.VoteResult;
import com.korit.moa.moa.repository.VoteResultRepository;
import com.korit.moa.moa.service.VoteResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class VoteResultServiceImplement implements VoteResultService {

    private final VoteResultRepository voteResultRepository;



    @Override
    public ResponseDto<VoteResultResponseDto> createVoteResult(String userId, VoteResultRequestDto dto) {
        VoteResultResponseDto data = null;
        Long voteId = dto.getVoteId();
        VoteAnswer voteAnswer = dto.getVoteAnswer();
        Date voteDate = dto.getVoteDate();

        try{
            VoteResult voteResult = VoteResult.builder()
                    .voteId(voteId)
                    .userId(userId)
                    .voteAnswer(voteAnswer)
                    .voteDate(voteDate)
                    .build();
            voteResultRepository.save(voteResult);

            data = new VoteResultResponseDto(voteResult);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }
}
