package com.korit.moa.moa.entity.userAnswer;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "User_Answers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserAnswer {
    @Id
    @Column(name = "answer_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long answerId;

    @Column(name = "group_id", nullable = false, unique = true)
    private Long groupId;

    @Column(name = "user_id", nullable = false, unique = true)
    private String userId;

    @Column(name = "user_answer", nullable = false)
    private String userAnswer;

    @Column(name = "answer_date", nullable = false)
    private LocalDate answerDate;

    @Column(name = "is_approved", nullable = false)
    private int isApproved = 2; //기본 값 2 -> 대기중
}
