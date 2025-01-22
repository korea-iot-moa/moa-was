package com.korit.moa.moa.service.implement;

import com.korit.moa.moa.common.constant.ResponseMessage;
import com.korit.moa.moa.dto.ResponseDto;
import com.korit.moa.moa.dto.vote_result.request.VoteResultRequestDto;
import com.korit.moa.moa.dto.vote_result.response.VoteResultGetResponseDto;
import com.korit.moa.moa.dto.vote_result.response.VoteResultResponseDto;
import com.korit.moa.moa.entity.voteResult.VoteAnswer;
import com.korit.moa.moa.entity.voteResult.VoteResult;
import com.korit.moa.moa.repository.VoteResultRepository;
import com.korit.moa.moa.service.VoteResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public ResponseDto<List<VoteResultGetResponseDto>> getVoteResult(Long voteId) {
        List<VoteResultGetResponseDto> data = new ArrayList<>();
        try {
            List<Object[]> results = voteResultRepository.findByVoteId(voteId);
            int total = results.stream().mapToInt(result -> ((Long) result[1]).intValue()).sum();
            data = results.stream()
                    .map(result -> new VoteResultGetResponseDto
                            (result[0].toString(), (Long) result[1], (double) ((Long) result[1]) / total * 100))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS,data);
    }


    @Override
    public ResponseDto<Boolean> existsByVoteIdAndUserId(String userId, Long voteId) {
        Boolean data = null;

        try{
            data = voteResultRepository.existsByUserIdAndVoteId(userId, voteId);

            if(data) {
                return ResponseDto.setSuccess(ResponseMessage.SUCCESS, true);
            }else {
                return  ResponseDto.setSuccess(ResponseMessage.SUCCESS, false);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
    }

}



















































































            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            


