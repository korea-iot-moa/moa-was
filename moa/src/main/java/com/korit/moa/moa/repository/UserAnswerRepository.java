package com.korit.moa.moa.repository;

import com.korit.moa.moa.entity.userAnswer.UserAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAnswerRepository extends JpaRepository<UserAnswer, Long> {
    boolean existsByGroupIdAndUserId(Long groupId, String userId);
}
