package com.korit.moa.moa.repository;

import com.korit.moa.moa.entity.userAnswer.UserAnswer;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserAnswerRepository extends JpaRepository<UserAnswer, Long> {
  
    List<UserAnswer> findAllByGroupId(Long groupId);

    boolean existsByGroupIdAndUserId(Long groupId, String userId);

    @Query("SELECT mg.groupId, mg.groupTitle, mg.groupType, mg.meetingType, " +
            "mg.groupCategory, mg.groupImage, ua.answerId, ua.answerDate, ua.isApproved " +
            "FROM UserAnswer ua JOIN MeetingGroup mg ON ua.groupId = mg.groupId " +
            "WHERE ua.userId = :userId " +
            "ORDER BY ua.answerDate DESC")
    List<Object[]> findParticipationStatus(@Param("userId") String userId);

    UserAnswer findByGroupIdAndUserId(Long groupId, @NotBlank String userId);

    @Query("""
    SELECT ua.groupId, mg.groupTitle, ua.userId, ua.isApproved
    FROM UserAnswer ua
    JOIN MeetingGroup mg ON ua.groupId = mg.groupId
    WHERE ua.groupId = :groupId
""")
    List<Object[]> findByGroupIdWithTitle(@Param("groupId") Long groupId);
}
