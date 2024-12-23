package com.korit.moa.moa.service.implement;

import com.korit.moa.moa.common.constant.ResponseMessage;
import com.korit.moa.moa.dto.ResponseDto;
import com.korit.moa.moa.dto.vote.request.RequestUpdateVoteDto;
import com.korit.moa.moa.dto.vote.request.RequestVoteDto;
import com.korit.moa.moa.dto.vote.response.VoteResponseDto;
import com.korit.moa.moa.entity.votes.Votes;
import com.korit.moa.moa.repository.VoteRepository;
import com.korit.moa.moa.service.VoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VoteServiceImplement implements VoteService {

    private final VoteRepository voteRepository;

    @Override
    public ResponseDto<VoteResponseDto> getMyGroupVote(Long groupId) {
        VoteResponseDto data = null;

        try {
            Optional<Votes> optionalResult = voteRepository.findVoteByGroupId(groupId);
            data = optionalResult.map(VoteResponseDto::new).orElse(null);
            return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
    }

    //투표 등록
    @Override
    public ResponseDto<VoteResponseDto> postMyGroupVote(RequestVoteDto dto) {
        VoteResponseDto  data = null;
        Long groupId = dto.getGroupId();
        String createId = dto.getCreateId();
        String voteContent = dto.getVoteContent();
        Date createDate = dto.getCreateDate();
        Date closeDate = dto.getCloseDate();

        if(voteContent == null){
            return ResponseDto.setFailed(ResponseMessage.VALIDATION_FAIL );
        }
        if(createDate == null ){
            return ResponseDto.setFailed(ResponseMessage.VALIDATION_FAIL );
        }
        if(closeDate == null ){
            return ResponseDto.setFailed(ResponseMessage.VALIDATION_FAIL );
        }
        try {
            Votes votes = Votes.builder()
                    .groupId(groupId)
                    .creatorId(createId)
                    .voteContent(voteContent)
                    .createDate(createDate)
                    .closeDate(closeDate)
                    .build();
            voteRepository.save(votes);
            data = new VoteResponseDto(votes);
            return ResponseDto.setSuccess(ResponseMessage.SUCCESS,data);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
    }

    @Override
    //투표  수정
    public ResponseDto<VoteResponseDto> updateMyGroupVote(Long voteId, RequestUpdateVoteDto dto) {
        VoteResponseDto data = null;
        try {
            Votes votes = voteRepository.findById(voteId)
                    .orElseThrow(() -> new IllegalAccessException("모임 투표를 찾을수 없습니다" + voteId) );

            votes.setVoteContent(dto.getVoteContent());
            System.out.println(dto.getVoteContent());
            votes.setCreateDate(dto.getCreateDate());
            System.out.println(dto.getCreateDate());
            votes.setCloseDate(dto.getCloseDate());
            System.out.println(dto.getCloseDate());

            voteRepository.save(votes);
            data = new VoteResponseDto(votes);

            return  ResponseDto.setSuccess(ResponseMessage.SUCCESS,data);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
    }

    //투표 삭제
    @Override
    public ResponseDto<Void> deleteMyGroupVote(Long voteId) {
        try {
            Optional<Votes> optionalVotes = voteRepository.findById(voteId);
            if(optionalVotes.isPresent()){
                voteRepository.deleteById(voteId);
            }
            return ResponseDto.setSuccess(ResponseMessage.SUCCESS,null);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
    }
}
