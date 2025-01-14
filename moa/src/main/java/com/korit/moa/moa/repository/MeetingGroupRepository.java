package com.korit.moa.moa.repository;

import com.korit.moa.moa.entity.meetingGroup.GroupCategory;
import com.korit.moa.moa.entity.meetingGroup.GroupTypeCategory;
import com.korit.moa.moa.entity.meetingGroup.MeetingGroup;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface MeetingGroupRepository extends JpaRepository<MeetingGroup,Long> {

    // 그룹 모임 홈화면 출력 사용자가 카테고리 선택한 경우/선택 안한 경우
    @Query(value = """
SELECT ranked.*
FROM (
    SELECT mg.*,
           ROW_NUMBER() OVER (PARTITION BY mg.group_category ORDER BY RAND()) AS rn
    FROM Meeting_Groups mg
    WHERE EXISTS (
        SELECT 1
        FROM Users u
        JOIN user_hobbies uh ON u.user_id = uh.user_id
        JOIN hobby h ON uh.hobby_id = h.hobby_id
        WHERE u.user_id = :userId
          AND (h.hobby_name = mg.group_category)
    )
    OR mg.group_category IN (
        SELECT group_category
        FROM (
            SELECT DISTINCT mg2.group_category
            FROM Meeting_Groups mg2
            ORDER BY RAND()
            LIMIT 3
        ) AS random_categories
    )
) AS ranked
WHERE ranked.rn <= 3
ORDER BY ranked.group_category, RAND();
""", nativeQuery = true)
    Optional<List<MeetingGroup>> findGroupByUserId(@Param("userId") String userId);

    // 그룹 모임 홈화면 출력 비로그인 상태
    @Query(value = """

SELECT ranked.*
       FROM (
           SELECT mg.*,
                  ROW_NUMBER() OVER (PARTITION BY mg.group_category ORDER BY RAND()) AS rn
           FROM Meeting_Groups mg
           WHERE EXISTS (
               SELECT 1
               FROM Users u
               WHERE mg.group_category IN (
                   SELECT group_category
                   FROM (
                       SELECT DISTINCT mg2.group_category
                       FROM Meeting_Groups mg2
                       ORDER BY RAND()
                       LIMIT 3
                   ) AS random_categories
               )
           )
       ) AS ranked
       WHERE ranked.rn <= 3
       ORDER BY ranked.group_category, RAND();
""", nativeQuery = true)
    Optional<List<MeetingGroup>> findGroupRandom();

    // 모임이름 필터링 검색
    @Query("SELECT m FROM MeetingGroup m " +
            "WHERE m.groupTitle LIKE %:keyword% " +
            "ORDER BY m.groupId")
    Optional<List<MeetingGroup>> findByGroupTitle(@Param("keyword") String keyword);

    // 모임 취미카테고리,지역칸테고리 필터링
    @Query("SELECT m FROM MeetingGroup m " +
            "WHERE m.groupCategory = :groupCategory " +
            "AND m.groupAddress Like CONCAT('%', :region, '%') " +
            "ORDER BY m.groupId ")
    Optional<List<MeetingGroup>> findByGroupCategoryAndRegion(@Param("groupCategory")GroupCategory groupCategory, @Param("region") String region);

    // 단기/정기 모임 필터
    Optional<List<MeetingGroup>> findByGroupType(@Param("groupType") GroupTypeCategory groupType);

    // 사용자가 관리자인지 확인
    Boolean existsByGroupIdAndCreatorId(Long groupId, String userId);

    //같은 제목 모임 생성 중복 방지
    boolean existsByGroupTitle(String groupTitle);


//    //특정 모임 생성자 찾기
//    @Query("SELECT mg.creatorId FROM MeetingGroup mg WHERE mg.groupId = :groupId")
//    String findByGroupId(@Param("groupId") Long groupId);

}