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
    private Long reportId;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "group_id", nullable = false)
    private Long groupId;

    @Column(name = "report_detail", nullable = false, columnDefinition = "TEXT")
    private String reportDetail;

    @Column(name = "report_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private ReportType reportType;

    @Column(name = "report_user", nullable = false) // 'report_user' 이름 사용
    private String reportUser;

    @Column(name = "report_image")
    private String reportImage;

    @Column(name = "report_result", nullable = false)
    @Enumerated(EnumType.STRING)
    private ReportResult reportResult;
}
