package com.korit.moa.moa.repository;

import com.korit.moa.moa.entity.userAnswer.UserAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserAnswerRepository extends JpaRepository<UserAnswer, Long> {
    List<UserAnswer> findAllByGroupId(Long groupId);

    void deleteByUserId(String userId);

    Optional<UserAnswer> findByGroupId(Long groupId);

//    boolean existsByGroupIdAndUserId(Long groupId, String userId);
}
