package com.korit.moa.moa.repository;

import com.korit.moa.moa.entity.votes.Votes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteRepository extends JpaRepository<Votes, Long> {
}
