package com.korit.moa.moa.entity.votes;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "Votes")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Votes {
    @Id
    @Column(name = "vote_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long voteId;

    @JoinColumn(name = "group_id", nullable = false)
    private Long groupId;

    @JoinColumn(name = "user_id", nullable = false)
    private String creatorId;

    @Column(name = "vote_content", nullable = false)
    private String voteContent;

    @Column(name = "vote_create_date", nullable = false)
    private Date createDate;

    @Column(name = "vote_close_date", nullable = false)
    private Date closeDate;

}
