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
//    //모임 개설
//    Object createGroupMeeting(@RequestBody MeetingGroup meetingGroup);
//
//    //모임 수정
//    Object updateMeetingGroupId(@RequestParam("groupId")Long groupId);
//
//    //모임 삭제
//    Object deleteMeetingGroupId(@PathVariable Long groupId);
//
//
//    // 그룹 모임 홈화면 출력 사용자가 카테고리 선택한 경우
//    @Query("SELECT groupId, group_title, groupAddress, meetingType, groupImage, groupDate" +
//            "FROM (SELECT mg.groupdId, u.userId, mg.groupTitle, mg.groupAddress, mg.groupCategory, mg.groupImage, mg.groupDate" +
//            "ROW_NUMBER OVER (PARTITION BY mg.group_category ORDER BY group_title) AS rn" +
//            "FROM MeetingGroup mg" +
//            "INNER JOIN Users u ON mg.group_category = u.hobby" +
//            "WHERE u.user_id = 'userId'" +
//            "AND mg.meetingType IN ('취미', '문화_예술', '스포츠_운동', '푸드_맛집', '자기계발', '여행', '연애', '힐링') ) AS ranked " +
//            "WHERE ranked.rn <= 3" +
//            "ORDER BY group_category, RAND()")
//    List<Object[]> findGroupHomeSelectByGroupId(@Param("groupId") Long goupId);
//
//    // 그룹 모임 홈화면 출력 사용자가 카테고리 선택한 경우
//    @Query("SELECT groupId, groupTitle, groupAddress, groupCategory, groupImage, groupDate" +
//            "FROM (SELECT groupId, groupTitle, groupAddress, groupCategory, groupImage, groupDate" +
//            "ROW_NUMBER() OVER (PARTITION BY mg.group_category ORDER BY group_title) AS rn" +
//            "FROM MeetingGroup" +
//            "WHERE mg.groupCategory IN ('취미', '문화_예술', '스포츠_운동', '푸드_맛집', '자기계발', '여행', '연애', '힐링')) AS ranked" +
//            "WHERE ranked.rn <= 3" +
//            "ORDER BY grouptCategory, RAND()")
//    List<Object[]> findGroupHomeNoSelectByGroupId(@Param("groupId") Long goupId);
}
