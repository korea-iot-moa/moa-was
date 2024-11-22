package com.korit.moa.moa.entity.voteResult;

import com.korit.moa.moa.entity.votes.Votes;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "Vote_Results")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VoteResult {
    @Id
    @Column(name = "vote_result_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long voteResultId;

    @Column(name = "vote_id", nullable = false)
    private Long voteId;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "vote_answer", nullable = false)
    private VoteAnswer voteAnswer;

    @Column(name = "vote_date", nullable = false)
    private Date voteDate;
}
