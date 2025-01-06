package com.korit.moa.moa.repository;

import com.korit.moa.moa.dto.black_list.response.ResponseGetBlackListDto;
import com.korit.moa.moa.entity.balckList.BlackList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlackListRepository extends JpaRepository<BlackList, Long> {
    //회원 중복 검사
    @Query("SELECT COUNT(bl) > 0 FROM BlackList bl WHERE bl.userId = :userId")
    boolean existsByUserId(@Param("userId") String userId);

    //블랙 리스트 조회
    @Query("""
SELECT u.userId, u.nickName, ul.userLevel
FROM BlackList bl
INNER JOIN User u ON bl.userId = u.userId
INNER JOIN UserList ul ON ul.user.userId = u.userId
WHERE bl.groupId = :groupId
""")
    List<Object[]> findByGroup(@Param("groupId") Long groupId);

    void deleteByGroupId(Long groupId);
}
