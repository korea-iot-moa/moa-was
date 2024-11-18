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

@Service
@RequiredArgsConstructor
public class MeetingGroupServiceImplement implements MeetingGroupService {

    public final MeetingGroupRepository meetingGroupRepository;

    @Override
    public ResponseDto<ResponseGroupDto> createMeetingGroup(String userId, RequestGroupDto dto) {
        Long groundId = dto.getGroupId();
        String creatorId = dto.getCreatorId();
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

        if (groupTitle != null && groupTitle.isEmpty()){
            return ResponseDto.setFailed(ResponseMessage.VALIDATION_FAIL);
        }
        if (groupContent != null && groupContent.isEmpty()){
            return ResponseDto.setFailed(ResponseMessage.VALIDATION_FAIL);
        }
        if (groupAddress != null && groupAddress.isEmpty()){
            return ResponseDto.setFailed(ResponseMessage.VALIDATION_FAIL);
        }
        if (groupType != null &&)

        try{
           MeetingGroup meetingGroup = MeetingGroup.builder()
                   .groupId(groundId)
                   .creatorId(creatorId)
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

           meetingGroupRepository.save(meetingGroup);
           ResponseGroupDto data = new ResponseGroupDto(meetingGroup);
            return ResponseDto.setSuccess(ResponseMessage.SUCCESS,data);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }

    }

    @Override
    public ResponseDto<ResponseGroupDto> updateMeetingGroup(String creatorId, Long groupId, RequestGroupDto dto) {

        if(creatorId != null && creatorId.isEmpty()){
            return ResponseDto.setFailed(ResponseMessage.MESSAGE_SEND_FAIL);
        }
        if (groupId != null ){
            return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_GROUP);
        }

        return null;
    }

    @Override
    public ResponseDto<ResponseGroupDto> deleteMeetingGroup(String creatorId, Long groupId) {

        if(creatorId != null && creatorId.isEmpty()){
            return ResponseDto.setFailed(ResponseMessage.MESSAGE_SEND_FAIL);
        }
        if (groupId != null ){
            return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_GROUP);
        }
        return null;
    }
}
