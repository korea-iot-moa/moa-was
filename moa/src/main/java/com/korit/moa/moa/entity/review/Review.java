package com.korit.moa.moa.entity.review;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


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

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "group_id",nullable = false)
    private Long groupId;

    @Column(name = "review_content", nullable = false, columnDefinition = "TEXT")
    private String reviewContent;

    @Column(name = "review_date", nullable = false)
    private String reviewDate;

    @Column(name = "review_image")
    private String reviewImage;


}
