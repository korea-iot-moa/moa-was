package com.korit.moa.moa.repository;

import com.korit.moa.moa.entity.balckList.BlackList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BlackListRepository extends JpaRepository<BlackList, Long> {

    @Query("""
 select distinct  bl.blackListId
 ,  u.userId
 ,COALESCE(u.profileImage, '') AS profileImage
 , u.nickName
FROM BlackList bl
INNER JOIN User u ON bl.userId = u.userId
WHERE bl.groupId = :groupId
""")
    List<Object[]> findByGroup(@Param("groupId") Long groupId);

    boolean existsByUserIdAndGroupId(String userId, Long groupId);

    @Query("SELECT bl FROM BlackList bl WHERE bl.groupId = :groupId AND bl.userId = :userId")
    Optional<BlackList> findByGroupIdAndUserId(@Param("groupId") Long groupId, @Param("userId") String userId);

}
