package com.korit.moa.moa.repository;

import com.korit.moa.moa.dto.user_answer.response.ParticipationStatusResponseDto;
import com.korit.moa.moa.entity.userAnswer.UserAnswer;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserAnswerRepository extends JpaRepository<UserAnswer, Long> {
  
    List<UserAnswer> findAllByGroupId(Long groupId);

    @Modifying
    @Transactional
    @Query("DELETE  FROM UserAnswer ua WHERE ua.userId = :userId  and ua.groupId = :groupId  ")
    void deleteByUserId(@Param("userId") String userId , @Param("groupId") Long groupId);

    // 사용자 답변 중복 확인
    boolean existsByGroupIdAndUserId(Long groupId, String userId);

    // 모임 신청 내역 확인
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
