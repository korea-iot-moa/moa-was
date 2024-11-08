package com.korit.moa.moa.entity.Report;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Table(name = "Reports")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @JoinColumn(name = "user_id",nullable = false)
    @Column(nullable = false, length = 255)
    private  String userId;

    @JoinColumn(name = "group_id",nullable = false)
    @Column(nullable = false)
    private  Long groupId;

    @Column(name = "report_detail",nullable = false, columnDefinition = "TEXT")
    private  String reportDetail;

    @Column(name = "report_user", nullable = false, length = 255)
    private String reportUser;
    @Column(name = "review_image")
    private  byte[] reviewImage;

}
