package com.korit.moa.moa.repository;


import com.korit.moa.moa.entity.meetingGroup.MeetingGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;




@Repository
public interface MeetingGroupRepository extends JpaRepository<MeetingGroup,Long> {

}
