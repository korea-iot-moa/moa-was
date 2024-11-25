package com.korit.moa.moa.service.implement;

import com.korit.moa.moa.common.constant.ResponseMessage;
import com.korit.moa.moa.dto.ResponseDto;
import com.korit.moa.moa.dto.group.request.RequestGroupDto;
import com.korit.moa.moa.dto.group.response.ResponseGroupDto;
import com.korit.moa.moa.entity.meetingGroup.GroupCategory;
import com.korit.moa.moa.entity.meetingGroup.GroupTypeCategory;
import com.korit.moa.moa.entity.meetingGroup.MeetingGroup;
import com.korit.moa.moa.entity.meetingGroup.MeetingTypeCategory;
import com.korit.moa.moa.repository.MeetingGroupRepository;
import com.korit.moa.moa.service.MeetingGroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MeetingGroupServiceImplement implements MeetingGroupService {

    public final MeetingGroupRepository meetingGroupRepository;

    @Override
    // 모임 생성
    public ResponseDto<ResponseGroupDto> createGroupMeeting(String userId, RequestGroupDto dto) {
        Long groundId = dto.getGroupId();
        String groupTitle = dto.getGroupTitle();
        String groupContent = dto.getGroupContent();
        String groupAddress = dto.getGroupAddress();
        String groupImage = dto.getGroupImage();
        String groupSupplies = dto.getGroupSupplies();
        String groupDate = dto.getGroupDate();
        String groupQuestion = dto.getGroupQuestion();
        GroupCategory groupCategory = dto.getGroupCategory();
        GroupTypeCategory groupType = dto.getGroupType();
        MeetingTypeCategory meetingType = dto.getMeetingType();

        if(groundId == null){
            return ResponseDto.setFailed(ResponseMessage.VALIDATION_FAIL + "1");
        }
        if (groupTitle == null || groupTitle.isEmpty()){
            return ResponseDto.setFailed(ResponseMessage.VALIDATION_FAIL + "2");
        }
        if (groupContent == null || groupContent.isEmpty()){
            return ResponseDto.setFailed(ResponseMessage.VALIDATION_FAIL + "3");
        }
        if (groupAddress == null || groupAddress.isEmpty()){
            return ResponseDto.setFailed(ResponseMessage.VALIDATION_FAIL + "4");
        }
        if (groupDate == null || groupDate.isEmpty()){
            return ResponseDto.setFailed(ResponseMessage.VALIDATION_FAIL + "5");
        }
        if (groupQuestion == null || groupQuestion.isEmpty()){
            return ResponseDto.setFailed(ResponseMessage.VALIDATION_FAIL + "6");
        }
        if (groupType == null){
            return ResponseDto.setFailed(ResponseMessage.VALIDATION_FAIL + "7");
        }
        if (meetingType == null){
            return ResponseDto.setFailed(ResponseMessage.VALIDATION_FAIL + "8");
        }

        try{
            MeetingGroup meetingGroup = MeetingGroup.builder()
                    .groupId(groundId)
                    .creatorId(userId)
                    .groupTitle(groupTitle)
                    .groupContent(groupContent)
                    .groupAddress(groupAddress)
                    .groupImage(groupImage)
                    .groupSupplies(groupSupplies)
                    .groupDate(groupDate)
                    .groupQuestion(groupQuestion)
                    .groupCategory(groupCategory)
                    .groupType(groupType)
                    .meetingType(meetingType)
                    .build();
            System.out.println(dto);
            meetingGroupRepository.save(meetingGroup);

            System.out.println(meetingGroupRepository);
            ResponseGroupDto data = new ResponseGroupDto(meetingGroup);
            return ResponseDto.setSuccess(ResponseMessage.SUCCESS,data);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }

    }

    @Override
    // 모임 수정
    public ResponseDto<ResponseGroupDto> updateMeetingGroupId(String userId, Long groupId, RequestGroupDto dto) {
        ResponseGroupDto data = null;
        if(userId == null || userId.isEmpty()){
            return ResponseDto.setFailed(ResponseMessage.MESSAGE_SEND_FAIL);
        }
        if (groupId == null ){
            return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_GROUP);
        }

        try {
            MeetingGroup meetingGroup =  meetingGroupRepository.findById(groupId)
                    .orElseThrow(() ->  new IllegalAccessException("모임을 찾을수 없습니다" + groupId));

            if(meetingGroup.getCreatorId().equals(userId)){
                meetingGroup.setGroupTitle(dto.getGroupTitle());
                meetingGroup.setGroupContent(dto.getGroupContent());
                meetingGroup.setGroupAddress(dto.getGroupAddress());
                meetingGroup.setGroupImage(dto.getGroupImage());
                meetingGroup.setGroupSupplies(dto.getGroupSupplies());
                meetingGroup.setGroupCategory(dto.getGroupCategory());
                meetingGroup.setGroupType(dto.getGroupType());
                meetingGroup.setMeetingType(dto.getMeetingType());

                meetingGroupRepository.save(meetingGroup);
                data = new ResponseGroupDto(meetingGroup);

                return  ResponseDto.setSuccess(ResponseMessage.SUCCESS,data);

            }else{
                return ResponseDto.setFailed(ResponseMessage.UNAUTHORIZED_USER);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
    }

    @Override
    // 모임 삭제
    public ResponseDto<Void> deleteMeetingGroupId(String userId, Long groupId) {

        if(userId == null || userId.isEmpty()){
            return ResponseDto.setFailed(ResponseMessage.MESSAGE_SEND_FAIL);
        }
        try {
            Optional<MeetingGroup> optionalMeetingGroup = meetingGroupRepository.findById(groupId);
            if(optionalMeetingGroup.isPresent() && optionalMeetingGroup.get().getCreatorId().equals(userId)){
                meetingGroupRepository.deleteById(groupId);
            }
            return ResponseDto.setSuccess(ResponseMessage.SUCCESS,null);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }

    }
}