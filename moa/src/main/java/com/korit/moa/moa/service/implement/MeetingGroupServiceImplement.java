package com.korit.moa.moa.service.implement;

import com.korit.moa.moa.common.constant.ResponseMessage;
import com.korit.moa.moa.dto.ResponseDto;
import com.korit.moa.moa.dto.group.request.RequestGroupDto;
import com.korit.moa.moa.dto.group.response.ResponseGroupDto;
import com.korit.moa.moa.dto.group.response.SearchResponseDto;
import com.korit.moa.moa.entity.meetingGroup.GroupCategory;
import com.korit.moa.moa.entity.meetingGroup.GroupTypeCategory;
import com.korit.moa.moa.entity.meetingGroup.MeetingGroup;
import com.korit.moa.moa.entity.meetingGroup.MeetingTypeCategory;
import com.korit.moa.moa.entity.user.User;
import com.korit.moa.moa.entity.userList.UserLevel;
import com.korit.moa.moa.entity.userList.UserList;
import com.korit.moa.moa.entity.userList.UserListId;
import com.korit.moa.moa.repository.MeetingGroupRepository;
import com.korit.moa.moa.repository.UserListRepository;
import com.korit.moa.moa.repository.UserRepository;
import com.korit.moa.moa.service.ImgFileService;
import com.korit.moa.moa.service.MeetingGroupService;
import io.lettuce.core.ScriptOutputType;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MeetingGroupServiceImplement implements MeetingGroupService {

    public final MeetingGroupRepository meetingGroupRepository;
    public final UserListRepository userListRepository;
    public final UserRepository userRepository;
    private final ImgFileService imgFileService;

    // 모임 생성
    @Override
    public ResponseDto<ResponseGroupDto> createGroupMeeting(String userId, RequestGroupDto dto) {

        String creatorId = userId;
        String groupTitle = dto.getGroupTitle();
        String groupContent = dto.getGroupContent();
        String groupAddress = dto.getGroupAddress();
        String groupSupplies = dto.getGroupSupplies();
        String groupDate = dto.getGroupDate();
        String groupQuestion = dto.getGroupQuestion();
        GroupCategory groupCategory = dto.getGroupCategory();
        GroupTypeCategory groupType = dto.getGroupType();
        MeetingTypeCategory meetingType = dto.getMeetingType();

        if(creatorId ==  null || creatorId.isEmpty()){
            return ResponseDto.setFailed((ResponseMessage.VALIDATION_FAIL));
        }
        if (groupTitle == null || groupTitle.isEmpty()){
            return ResponseDto.setFailed(ResponseMessage.VALIDATION_FAIL );
        }
        if (groupContent == null || groupContent.isEmpty()){
            return ResponseDto.setFailed(ResponseMessage.VALIDATION_FAIL );
        }
        if (groupAddress == null || groupAddress.isEmpty()){
            return ResponseDto.setFailed(ResponseMessage.VALIDATION_FAIL );
        }
        if (groupDate == null || groupDate.isEmpty()){
            return ResponseDto.setFailed(ResponseMessage.VALIDATION_FAIL );
        }
        if (groupQuestion == null || groupQuestion.isEmpty()){
            return ResponseDto.setFailed(ResponseMessage.VALIDATION_FAIL );
        }
        if (groupType == null){
            return ResponseDto.setFailed(ResponseMessage.VALIDATION_FAIL );
        }
        if (meetingType == null){
            return ResponseDto.setFailed(ResponseMessage.VALIDATION_FAIL );
        }
        if(meetingGroupRepository.existsByGroupTitle(dto.getGroupTitle())){
            return ResponseDto.setFailed(ResponseMessage.EXIST_DATA);
        }

        try{
            String groupImgPath = null;
            if(dto.getGroupImage() != null) {
                groupImgPath = imgFileService.convertImgFile(dto.getGroupImage(), "groupImg");
            }

            MeetingGroup meetingGroup = MeetingGroup.builder()
                    .creatorId(userId)
                    .groupTitle(groupTitle)
                    .groupContent(groupContent)
                    .groupAddress(groupAddress)
                    .groupImage(groupImgPath)
                    .groupSupplies(groupSupplies)
                    .groupDate(groupDate)
                    .groupQuestion(groupQuestion)
                    .groupCategory(groupCategory)
                    .groupType(groupType)
                    .meetingType(meetingType)
                    .build();

            meetingGroupRepository.save(meetingGroup);


            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));
            String profileImgPath = user.getProfileImage();

            UserListId userListId = new UserListId();
            userListId.setGroupId(meetingGroup.getGroupId());
            userListId.setUserId(creatorId);

            String nickName =  userRepository.findByUserNickName(userId);
            UserList userList =  UserList.builder()
                    .id(userListId)
                    .user(User.builder().userId(creatorId).build())
                    .group(meetingGroup)
                    .userLevel(UserLevel.관리자)
                    .nickName(nickName)
                    .joinDate(new Date())
                    .profileImage(profileImgPath)
                    .build();
            userListRepository.save(userList);
            ResponseGroupDto data =  new ResponseGroupDto(meetingGroup);

            return ResponseDto.setSuccess(ResponseMessage.SUCCESS,data);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }

    }

    // 모임 수정
    @Override
    public ResponseDto<ResponseGroupDto> updateMeetingGroupId(Long groupId, RequestGroupDto dto) {
        ResponseGroupDto data = null;

        if (groupId == null ){
            return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_GROUP);
        }

        try {
            MeetingGroup meetingGroup =  meetingGroupRepository.findById(groupId)
                    .orElseThrow(() ->  new IllegalAccessException("모임을 찾을수 없습니다" + groupId));

            String groupImgPath = null;
            if (dto.getGroupImage() != null) {
                groupImgPath = imgFileService.convertImgFile(dto.getGroupImage(), "groupImg");
            }
            System.out.println(groupImgPath);
            meetingGroup.setGroupTitle(dto.getGroupTitle() != null ?
                    dto.getGroupTitle() : meetingGroup.getGroupTitle());
            meetingGroup.setGroupContent(dto.getGroupContent() != null ?
                    dto.getGroupContent() : meetingGroup.getGroupContent());
            meetingGroup.setGroupAddress(dto.getGroupAddress() != null ?
                    dto.getGroupAddress() : meetingGroup.getGroupAddress());
            meetingGroup.setGroupImage(groupImgPath != null ?
                    groupImgPath : meetingGroup.getGroupImage());
            meetingGroup.setGroupSupplies(dto.getGroupSupplies() != null ?
                    dto.getGroupSupplies() : meetingGroup.getGroupSupplies());
            meetingGroup.setGroupCategory(dto.getGroupCategory() != null ?
                    dto.getGroupCategory() : meetingGroup.getGroupCategory());
            meetingGroup.setGroupType(dto.getGroupType() != null ?
                    dto.getGroupType() : meetingGroup.getGroupType());
            meetingGroup.setMeetingType(dto.getMeetingType() != null ?
                    dto.getMeetingType() : meetingGroup.getMeetingType());


            meetingGroupRepository.save(meetingGroup);
            data = new ResponseGroupDto(meetingGroup);

            return  ResponseDto.setSuccess(ResponseMessage.SUCCESS,data);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
    }

    // 모임 삭제
    @Override
    @Transactional
    public ResponseDto<Void> deleteMeetingGroupId( Long groupId) {
        if(groupId == null){
            return ResponseDto.setFailed(ResponseMessage.MESSAGE_SEND_FAIL);
        }
        try {
            Optional<MeetingGroup> optionalMeetingGroup = meetingGroupRepository.findById(groupId);
            if(optionalMeetingGroup.isPresent()){
                meetingGroupRepository.deleteById(groupId);
            }
            return ResponseDto.setSuccess(ResponseMessage.SUCCESS,null);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }

    }

  // 그룹 모임 홈화면 출력 사용자가 카테고리 선택한 경우/안한 경우
    @Override
    public ResponseDto<List<SearchResponseDto>> getGroupAtHome(String userId) {
        List<SearchResponseDto> data = null;

        if(userId == null) {
            return ResponseDto.setFailed(ResponseMessage.NO_PERMISSION);
        }

        try {
            Optional<List<MeetingGroup>> optionalMeetingGroups = meetingGroupRepository.findGroupByUserId(userId);

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

    // 그룹 모임 홈화면 출력 로그아웃
    @Override
    public ResponseDto<List<SearchResponseDto>> getGroupAtHomeAuth() {
        List<SearchResponseDto> data = null;

        try {
            Optional<List<MeetingGroup>> optionalMeetingGroups = meetingGroupRepository.findGroupRandom();

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



    // 검색어 필터링
    @Override
    public ResponseDto<List<SearchResponseDto>> findByGroupTitle(String keyword) {
        List<SearchResponseDto> data = null;

        if (keyword == null || keyword.trim().isEmpty()) {
            return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_DATA);
        }

        try {
            Optional<List<MeetingGroup>> optionalMeetingGroups = meetingGroupRepository.findByGroupTitle(keyword);

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

    // 단기/정기 필터링
    @Override
    public ResponseDto<List<SearchResponseDto>> findByGroupType(GroupTypeCategory groupType) {
        String groupTypes  = groupType.toString();

        List<SearchResponseDto> data = null;

        if(groupTypes == null) {
            return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_DATA);
        }

        try {
            Optional<List<MeetingGroup>> optionalMeetingGroups = meetingGroupRepository.findByGroupType(groupType);

            if(optionalMeetingGroups.isPresent()) {
                List<MeetingGroup> meetingGroups = optionalMeetingGroups.get();

                data = meetingGroups.stream()
                        .map(SearchResponseDto::new)
                        .collect(Collectors.toList());
            }

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    // 취미카테고리/지역카테고리
    @Override
    public ResponseDto<List<SearchResponseDto>> findByGroupCategoryAndRegion(GroupCategory groupCategory, String region) {
        List<SearchResponseDto> data = null;

        if (groupCategory == null || groupCategory.toString().isEmpty()) {
            return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_DATA);
        }
        if (region == null || region.toString().trim().isEmpty()) {
            return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_DATA);
        }

        try {
            Optional<List<MeetingGroup>> optionalMeetingGroups = meetingGroupRepository.findByGroupCategoryAndRegion(groupCategory, region);

            if(optionalMeetingGroups.isPresent()) {
                List<MeetingGroup> meetingGroups = optionalMeetingGroups.get();

                data = meetingGroups.stream()
                        .map(SearchResponseDto::new)
                        .collect(Collectors.toList());
            } else {
                return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_GROUP);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }





    // 그룹 단건 조회
    @Override
    public ResponseDto<ResponseGroupDto> getGroupById(Long groupId) {
        ResponseGroupDto data = null;

        try{
            Optional<MeetingGroup> optionalGroup = meetingGroupRepository.findById(groupId);
            if(optionalGroup.isEmpty()) {
                return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_DATA);
            }

            MeetingGroup meetingGroup = optionalGroup.get();
            data = new ResponseGroupDto(meetingGroup);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    @Override
    public ResponseDto<Boolean> isCreator(Long groupId, String userId) {
        Boolean data = null;

        try{
            data = meetingGroupRepository.existsByGroupIdAndCreatorId(groupId, userId);

            if (data) {
                return ResponseDto.setSuccess(ResponseMessage.SUCCESS, true);
            } else {
                return  ResponseDto.setSuccess(ResponseMessage.SUCCESS, false);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
    }

}