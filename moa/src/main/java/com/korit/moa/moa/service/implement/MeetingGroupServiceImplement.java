package com.korit.moa.moa.service.implement;

import com.korit.moa.moa.common.constant.ResponseMessage;
import com.korit.moa.moa.dto.ResponseDto;
import com.korit.moa.moa.dto.group.response.ResponseGroupDto;
import com.korit.moa.moa.dto.group.response.SearchResponseDto;
import com.korit.moa.moa.entity.meetingGroup.MeetingGroup;
import com.korit.moa.moa.repository.MeetingGroupRepository;
import com.korit.moa.moa.service.MeetingGroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MeetingGroupServiceImplement implements MeetingGroupService {

    public final MeetingGroupRepository meetingGroupRepository;

    @Override
    public ResponseDto<List<ResponseGroupDto>> findGroupHomeSelectByUserId(Long userId) {
        List<ResponseGroupDto> data = null;
//
//        try {
//            Optional<List<MeetingGroup>> optionalMeetingGroups = meetingGroupRepository.findGroupHomeSelectByUserId(userId);
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
        return null;
    }

    @Override
    public ResponseDto<List<SearchResponseDto>> findGroupSearchByGroupTitle(String groupTitle) {
        List<SearchResponseDto> data = null;
        String searchKeyword = groupTitle;
        try {
            Optional<List<MeetingGroup>> optionalMeetingGroups = meetingGroupRepository.findGroupSearchByGroupTitle(searchKeyword);

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
