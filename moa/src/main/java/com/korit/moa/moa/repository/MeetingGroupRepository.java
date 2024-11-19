package com.korit.moa.moa.repository;

import com.korit.moa.moa.dto.group.request.RequestGroupDto;
import com.korit.moa.moa.entity.meetingGroup.MeetingGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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


    // 그룹 모임 홈화면 출력 사용자가 카테고리 선택한 경우
    @Query(value = "SELECT ranked.groupId, ranked.groupTitle, ranked.groupAddress, ranked.meetingType, ranked.groupImage, ranked.groupDate, ranked.groupCategory " +
            "FROM (SELECT mg.groupId, u.userId, mg.groupTitle, mg.groupAddress, mg.meetingType, mg.groupCategory, mg.groupImage, mg.groupDate, " +
            "ROW_NUMBER() OVER (PARTITION BY mg.groupCategory ORDER BY mg.groupTitle) AS rn " +
            "FROM MeetingGroup mg " +
            "INNER JOIN User u ON mg.groupCategory = u.hobbies " +
            "WHERE u.userId = 'userId' " +
            "AND mg.groupCategory = (:mg.groupCategory) ) AS ranked " +
            "WHERE ranked.rn <= 3" +
            "ORDER BY ranked.groupCategory, RAND()", nativeQuery = true)
    List<Object[]> findGroupHomeSelectByUserId(@Param("userId") Long userId);

    // 그룹 모임 홈화면 출력 사용자가 카테고리 선택 안한 경우
    @Query(value = "SELECT groupId, groupTitle, groupAddress, groupCategory, groupImage, groupDate " +
            "FROM (SELECT groupId, groupTitle, groupAddress, groupCategory, groupImage, groupDate " +
            "ROW_NUMBER() OVER (PARTITION BY mg.group_category ORDER BY group_title) AS rn " +
            "FROM MeetingGroup" +
            "WHERE mg.groupCategory = (:mg.groupCategory) ) AS ranked " +
            "WHERE ranked.rn <= 3 " +
            "ORDER BY grouptCategory, RAND()", nativeQuery = true)
    List<Object[]> findGroupHomeNoSelectByGroupId(@Param("groupId") Long groupId);
}
