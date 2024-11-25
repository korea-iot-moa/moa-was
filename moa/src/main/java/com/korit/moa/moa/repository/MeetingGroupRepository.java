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
import java.util.Optional;

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


//   // 그룹 모임 홈화면 출력 사용자가 카테고리 선택한 경우
//    @Query("SELECT ranked.groupId, ranked.groupTitle, ranked.groupAddress, ranked.meetingType, ranked.groupImage, ranked.groupDate, ranked.groupCategory " +
//            "FROM ( " +
//            "  SELECT mg.groupId, mg.groupTitle, mg.groupAddress, mg.meetingType, mg.groupCategory, mg.groupImage, mg.groupDate, " +
//            "         ROW_NUMBER() OVER (PARTITION BY mg.groupCategory ORDER BY mg.groupTitle) AS rn " +
//            "  FROM MeetingGroup mg " +
//            "  INNER JOIN User u ON mg.groupCategory = u.hobbies " +
//            "  WHERE u.userId = :userId " +
//            ") AS ranked " +
//            "WHERE ranked.rn <= 3 " +
//            "ORDER BY ranked.groupCategory, RAND()")
//    Optional<List<MeetingGroup>> findHomeSelectByUserId(@Param("userId") String userId);

////     그룹 모임 홈화면 출력 사용자가 카테고리 선택 안한 경우
//    @Query("SELECT ranked.groupId, ranked.groupTitle, ranked.groupAddress, ranked.meetingType, ranked.groupImage, ranked.groupDate, ranked.groupCategory " +
//            "FROM ( " +
//            "  SELECT groupId, groupTitle, groupAddress, meetingType, groupCategory, groupImage, groupDate, " +
//            "         ROW_NUMBER() OVER (PARTITION BY groupCategory ORDER BY groupTitle) AS rn " +
//            "  FROM MeetingGroup " +
//            ") AS ranked " +
//            "WHERE ranked.rn <= 3 " +
//            "ORDER BY ranked.groupCategory, RAND()")
//    Optional<List<MeetingGroup>> findNoSelectByGroupId(@Param("groupId") Long groupId);

    // 모임 필터링 검색
    @Query("SELECT m FROM MeetingGroup m " +
            "WHERE m.groupTitle LIKE %:groupTitle% " +
            "ORDER BY m.groupId")
    Optional<List<MeetingGroup>> findByGroupTitle(@Param("groupTitle") String groupTitle);


}
