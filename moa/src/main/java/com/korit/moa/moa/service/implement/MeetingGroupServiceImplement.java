package com.korit.moa.moa.service.implement;

import com.korit.moa.moa.common.constant.ResponseMessage;
import com.korit.moa.moa.dto.ResponseDto;
import com.korit.moa.moa.dto.group.response.ResponseGroupDto;
import com.korit.moa.moa.dto.group.response.SearchResponseDto;
import com.korit.moa.moa.entity.meetingGroup.MeetingGroup;
import com.korit.moa.moa.repository.MeetingGroupRepository;
import com.korit.moa.moa.service.meetingGroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MeetingGroupServiceImplement implements meetingGroupService {

    public final MeetingGroupRepository meetingGroupRepository;

//    @Override
//    public ResponseDto<List<ResponseGroupDto>> findHomeSelectByUserId(String userId) {
//        List<ResponseGroupDto> data = null;
//
//        try {
//            Optional<List<MeetingGroup>> optionalMeetingGroups = meetingGroupRepository.findHomeSelectByUserId(userId);
//
//            if(optionalMeetingGroups.isPresent()) {
//                List<MeetingGroup> meetingGroups = optionalMeetingGroups.get();
//
//                data = meetingGroups.stream()
//                        .map(ResponseGroupDto::new)
//                        .collect(Collectors.toList());
//            } else {
//                return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_GROUP);
//            }
//
//        } catch(Exception e) {
//            e.printStackTrace();
//            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
//        }
//        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
//
//    }

    @Override
    public ResponseDto<List<SearchResponseDto>> findByGroupTitle(String groupTitle) {
        List<SearchResponseDto> data = null;

        if (groupTitle == null || groupTitle.trim().isEmpty()) {
            return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_DATA);
        }

        try {
            Optional<List<MeetingGroup>> optionalMeetingGroups = meetingGroupRepository.findByGroupTitle(groupTitle);

            if(optionalMeetingGroups.isPresent()) {
                List<MeetingGroup> meetingGroups = optionalMeetingGroups.get();

                data = meetingGroups.stream()
                        .map(SearchResponseDto::new)
                        .collect(Collectors.toList());
            } else {
                return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_GROUP);
            }
        } catch(Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

}
