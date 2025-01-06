package com.korit.moa.moa.repository;

import com.korit.moa.moa.entity.userAnswer.UserAnswer;
import jakarta.transaction.Transactional;
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
    @Query("DELETE FROM UserAnswer ua WHERE ua.userId = :userId")
    void deleteByUserId(@Param("userId") String userId);

    Optional<UserAnswer> findByGroupId(Long groupId);

    // 사용자 답변 중복 확인
    boolean existsByGroupIdAndUserId(Long groupId, String userId);

}
