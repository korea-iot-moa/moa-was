package com.korit.moa.moa.repository;

import com.korit.moa.moa.dto.group.request.RequestGroupDto;
import com.korit.moa.moa.entity.meetingGroup.MeetingGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;



import java.util.List;

@Repository
public interface MeetingGroupRepository extends JpaRepository<MeetingGroup,Long> {
    //모임 개설
    Object createGroupMeeting(@RequestBody MeetingGroup meetingGroup);

    //모임 수정
    Object updateMeetingGroupId(@RequestParam("groupId")Long groupId);

    //모임 삭제
    Object deleteMeetingGroupId(@PathVariable Long groupId);


}
