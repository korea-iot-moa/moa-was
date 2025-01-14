package com.korit.moa.moa.service.implement;

import com.korit.moa.moa.common.constant.ResponseMessage;
import com.korit.moa.moa.dto.ResponseDto;
import com.korit.moa.moa.dto.vote.request.RequestUpdateVoteDto;
import com.korit.moa.moa.dto.vote.request.RequestVoteDto;
import com.korit.moa.moa.dto.vote.response.PostVoteResponseDto;
import com.korit.moa.moa.dto.vote.response.VoteResponseDto;
import com.korit.moa.moa.entity.meetingGroup.MeetingGroup;
import com.korit.moa.moa.entity.votes.Votes;
import com.korit.moa.moa.repository.MeetingGroupRepository;
import com.korit.moa.moa.repository.VoteRepository;
import com.korit.moa.moa.service.VoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class  VoteServiceImplement implements VoteService {

    private final VoteRepository voteRepository;
    private final MeetingGroupRepository meetingGroupRepository;

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
    public ResponseDto<PostVoteResponseDto> postMyGroupVote(RequestVoteDto dto, String userId) {
        PostVoteResponseDto data = null;
        Long groupId = dto.getGroupId();
        String voteContent = dto.getVoteContent();
        Date createDate = dto.getCreateDate();
        Date closeDate = dto.getCloseDate();

        if (voteContent == null || createDate == null || closeDate == null) {
            return ResponseDto.setFailed(ResponseMessage.VALIDATION_FAIL);
        }
        if(createDate == null ){
            return ResponseDto.setFailed(ResponseMessage.VALIDATION_FAIL );
        }
        if(closeDate == null ){
            return ResponseDto.setFailed(ResponseMessage.VALIDATION_FAIL );
        }
        try {
            Boolean optionalCreatorId = meetingGroupRepository.existsByGroupIdAndCreatorId(groupId, userId);
            if (optionalCreatorId) {

                Votes votes = Votes.builder()
                        .groupId(groupId)
                        .creatorId(userId)
                        .voteContent(voteContent)
                        .createDate(createDate)
                        .closeDate(closeDate)
                        .build();
                voteRepository.save(votes);

                data = new PostVoteResponseDto(votes);
            }
            return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);

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
            votes.setCreateDate(dto.getCreateDate());
            votes.setCloseDate(dto.getCloseDate());

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

    @Override
    public ResponseDto<Boolean> existsVote(Long groupId) {
        Boolean data = null;

        try {
            data = voteRepository.existsByGroupId(groupId);

            if(data) {
                return ResponseDto.setSuccess(ResponseMessage.SUCCESS, true);
            } else {
                return ResponseDto.setSuccess(ResponseMessage.SUCCESS, false);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
    }
}
