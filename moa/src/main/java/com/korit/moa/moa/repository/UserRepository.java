package com.korit.moa.moa.repository;

import com.korit.moa.moa.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    // 사용자 검색
    Optional<User> findByUserId(String userId);

}
