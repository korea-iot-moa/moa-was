package com.korit.moa.moa.repository;

import com.korit.moa.moa.entity.recommendation.Recommendation;
import com.korit.moa.moa.entity.recommendation.RecommendationsId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecommendationRepository extends JpaRepository<Recommendation, RecommendationsId> {
}
