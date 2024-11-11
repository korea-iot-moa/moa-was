package com.korit.moa.moa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "reviews")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId;

    @JoinColumn(name = "user_id", nullable = false,  referencedColumnName = "user_id")
    private String userId;

    @JoinColumn(name = "group_id",nullable = false , referencedColumnName = "user_id")
    private Long groupId;

    @Column(name = "review_content", nullable = false, columnDefinition = "TEXT")
    private String reviewContent;

    @Column(name = "review_date", nullable = false)
    private Date reviewDate;

    @Column(name = "review_image")
    private String reviewImage;


}
