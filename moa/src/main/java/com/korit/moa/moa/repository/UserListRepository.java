package com.korit.moa.moa.repository;

import com.korit.moa.moa.entity.user.User;
import com.korit.moa.moa.entity.userAnswer.UserAnswer;
import com.korit.moa.moa.entity.userList.UserList;
import com.korit.moa.moa.entity.userList.UserListId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserListRepository extends JpaRepository<UserList, UserListId> {




    // 모임 내 유저 조회
    @Query(
            "SELECT u " +
            "FROM User u JOIN UserList ul ON u.userId = ul.user.userId " +
            "WHERE ul.group.groupId = :groupId"
    )
    List<Object[]> findUsersByGroupId(@Param("groupId") Long groupId);

    // 내 모임 조회
    @Query(
            "SELECT g.groupId, g.groupImage, g.groupTitle " +
            "FROM MeetingGroup g JOIN UserList ul ON g.groupId = ul.group.groupId " +
            "WHERE ul.user.userId = :userId"
    )
    List<Object[]> findGroupByUserId(@Param("userId") String userId);

//    // 데이터 베이스 존재 확인
//    boolean existsByUserIdAndGroupId(String userId, Long groupId);

    // 모임 나가기
    @Modifying
    @Query("DELETE FROM UserList ul WHERE ul.user.userId = :userId AND ul.group.groupId = :groupId")
    void deleteUserList(@Param("userId") String userId, @Param("groupId") Long groupId);

    //모임 내 남녀 성비 차트
    @Query( "SELECT u.userGender, COUNT(u) " +
            "FROM UserList ul JOIN User u ON u.userId = ul.user.userId  " +
            "WHERE ul.group.groupId = :groupId GROUP BY u.userGender" )
    List<Object[]> findGroupByUserGenderWithCount(@Param("groupId") Long groupId);

    //모임내 유입율 차트
    @Query(value = """
        SELECT 
            CONCAT(YEAR(ul.join_date), '-', CEIL(MONTH(ul.join_date) / 3)) AS quarter,
            COUNT(*) AS user_count,
            (COUNT(*) * 1.0 / (SELECT COUNT(*) FROM User_List WHERE group_id = :groupId)) * 100 AS ratio
        FROM 
            User_List ul
        WHERE
            ul.group_id = :groupId
        GROUP BY
            CONCAT(YEAR(ul.join_date), '-', CEIL(MONTH(ul.join_date) / 3))
        ORDER BY 
            quarter ASC
        """, nativeQuery = true)
    List<Object[]> getQuarterlyData(@Param("groupId") Long groupId);

    //유저 등급 수정
    @Query("SELECT ul FROM UserList ul WHERE ul.group.groupId = :groupId")
    Optional<UserList> findByGroupId(Long groupId);

    //유저 추방
    @Modifying
    @Query("DELETE FROM UserList ul WHERE ul.user.userId = :userId")
    void deleteByUserId(@Param("userId") String userId);



}
