package com.korit.moa.moa.repository;

import com.korit.moa.moa.dto.auth.request.FindIdRequestDto;
import com.korit.moa.moa.entity.user.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    // 사용자 검색
    Optional<User> findByUserId(String userId);
    // 닉네임중복 확인
    boolean existsByNickName(String nickName);

    // 아이디 찾기
    @Query(
            "SELECT u FROM User u" +
                    " WHERE u.userName = :userName AND u.userBirthDate = :userBirthdate "
    )
    Optional<User> findByNameAndBirthDate(@Param("userName") String userName, @Param("userBirthdate") Date userBirthDate);

}
