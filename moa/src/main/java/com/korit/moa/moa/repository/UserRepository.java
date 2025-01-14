package com.korit.moa.moa.repository;

import com.korit.moa.moa.dto.auth.request.FindIdRequestDto;
import com.korit.moa.moa.entity.meetingGroup.MeetingGroup;
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

    boolean existsByUserId(String userId);

    // 아이디 찾기
    @Query(
            "SELECT u FROM User u" +
                    " WHERE u.userName = :userName AND u.phoneNumber = :phoneNumber "
    )
    Optional<User> findByUserNameAndUserBirthDate(@Param("userName") String userName, @Param("phoneNumber") String phoneNumber);

    // 이메일 인증시 회원 정보 확인
    Optional<User>  findByUserIdAndUserName(String userId, String userName);

    @Query("""
     select u.nickName
     from User u
     where u.userId = :userId
""")
        //모임 생성 후 생성자의 닉네임을 유저리스트에 등록
    String findByUserNickName(@Param("userId") String userId);

    User findBySnsIdAndJoinPath(String snsId, String registration);

    // 회원탈퇴
    @Modifying
    @Query("DELETE FROM User u WHERE u.userId = :userId")
    void deleteUser(@Param("userId") String userId);
}