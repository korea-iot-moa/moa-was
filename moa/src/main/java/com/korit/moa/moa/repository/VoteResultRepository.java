package com.korit.moa.moa.repository;

import com.korit.moa.moa.entity.voteResult.VoteResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface VoteResultRepository extends JpaRepository<VoteResult, Long> {

    @Query("SELECT vr.voteAnswer " +
            "FROM VoteResult vr " +
            "WHERE vr.voteId = :voteId")
    List<VoteResult> findByGroupId(@Param("voteId") Long voteId);
}
