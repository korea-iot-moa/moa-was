package com.korit.moa.moa.repository;

import com.korit.moa.moa.entity.meetingGroup.GroupCategory;
import com.korit.moa.moa.entity.meetingGroup.GroupTypeCategory;
import com.korit.moa.moa.entity.recommendation.Recommendation;
import com.korit.moa.moa.entity.recommendation.RecommendationsId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public interface RecommendationRepository extends JpaRepository<Recommendation, RecommendationsId> {


//    // 추천순 제목
//    @Query(value= """
//SELECT DISTINCT mg.group_id, mg.creator_id, mg.group_title, mg.group_content, mg.group_address,
//                       mg.group_image, mg.group_supplies, mg.group_date, mg.group_category,
//                       mg.group_type, mg.meeting_type, count(reco.user_id) As Recommendation_count
//FROM meeting_groups mg
//LEFT JOIN Recommendations reco ON mg.group_id = reco.group_id
//LEFT JOIN Users u ON reco.user_id = u.user_id
//GROUP BY mg.group_id
//ORDER BY Recommendation_count DESC;
//""", nativeQuery = true)
//    List<Object[]> findAllGroup();

    @Query(value= """
SELECT mg.group_id, u.user_id, mg.group_title, mg.group_content, mg.group_address,
       mg.group_image, mg.group_supplies, mg.group_date, mg.group_category,
       mg.group_type, mg.meeting_type
FROM Recommendations reco
JOIN meeting_groups mg ON reco.group_id = mg.group_id
JOIN Users u ON reco.user_id = u.user_id
WHERE reco.user_id = :userId
ORDER BY mg.group_id;
""", nativeQuery = true)
    List<Object[]> findByUserId(@Param("userId") String userId);

}
