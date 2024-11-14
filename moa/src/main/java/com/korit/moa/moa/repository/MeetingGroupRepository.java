package com.korit.moa.moa.repository;

import com.korit.moa.moa.entity.meetingGroup.MeetingGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MeetingGroupRepository extends JpaRepository<MeetingGroup,Long> {

 
}
