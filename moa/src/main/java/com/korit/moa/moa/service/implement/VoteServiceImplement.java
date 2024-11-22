package com.korit.moa.moa.service.implement;

import com.korit.moa.moa.common.constant.ResponseMessage;
import com.korit.moa.moa.dto.ResponseDto;
import com.korit.moa.moa.dto.vote.response.VoteResponseDto;
import com.korit.moa.moa.entity.votes.Votes;
import com.korit.moa.moa.repository.VoteRepository;
import com.korit.moa.moa.service.VoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VoteServiceImplement implements VoteService {

    private final VoteRepository voteRepository;

    @Override
    public ResponseDto<VoteResponseDto> getMyGroupVote(Long groupId) {
        VoteResponseDto data = null;

        try{
            Optional<Object> optionalResult = voteRepository.findVoteByGroupId(groupId);

            data = optionalResult
                    .map(result -> (Votes) result)
                    .map(VoteResponseDto :: new)
                    .orElse(null);


        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }
}
