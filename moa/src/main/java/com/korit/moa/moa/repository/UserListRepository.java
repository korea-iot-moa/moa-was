package com.korit.moa.moa.repository;

import com.korit.moa.moa.entity.user.User;
import com.korit.moa.moa.entity.userList.UserList;
import com.korit.moa.moa.entity.userList.UserListId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserListRepository extends JpaRepository<UserList, UserListId> {

    // 모임 내 유저 조회
    @Query(
            "SELECT u.userId, u.nickName, u.profileImage " +
            "FROM User u JOIN UserList ul ON u.userId = ul.userId " +
            "WHERE ul.groupId = :groupId"
    )
    List<Object[]> findUsersByGroupId(@Param("groupId") Long groupId);

    // 내 모임 조회
    @Query("SELECT g.groupId, g.groupImage, g.groupTitle " +
            "FROM MeetingGroup g JOIN UserList ul ON g.groupId = ul.groupId " +
            "WHERE ul.user.userId = :userId")
    List<Object[]> findGroupByUserId(@Param("userId") String userId);
}
