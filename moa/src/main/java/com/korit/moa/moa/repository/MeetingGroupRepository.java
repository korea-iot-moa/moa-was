package com.korit.moa.moa.repository;

import com.korit.moa.moa.dto.group.request.RequestGroupDto;
import com.korit.moa.moa.entity.meetingGroup.MeetingGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Repository
public interface MeetingGroupRepository extends JpaRepository<MeetingGroup,Long> {
    //모임 삭제
    @Query(
            "DELETE FROM MeetingGroup mg  " +
                    "WHERE  mg.groudId ='' AND mg.creatorId = '관리자'"
    )
    Object deleteMeetingGroupId(@PathVariable Long groupId);

    //모임 수정
    @Query(
            "UPDATE MeetingGroup mg SET " +
                    "mg.groupTitle = dto.getTitle(), mg.groupContent =dto.ge(), " +
                    "mg.groupAddress =dto.dto.getAddress(), mg.groupImage= dto.getImage(), " +
                    "mg.groupSupplies = dto.getSuplies(), mg.groupDate = dto.getDate()," +
                    "mg.groupCategory=dto.getCategory(),mg.groupType " +
    )
    Object updateMeetingGroupId(@Param("groupId")@RequestParam groupId);

}
