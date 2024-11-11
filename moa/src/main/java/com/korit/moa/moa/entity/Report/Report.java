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
    private  Long reportId;

    @JoinColumn(name = "user_id", nullable = false, referencedColumnName = "user_id")
    private  String userId;

    @JoinColumn(name = "group_id", nullable = false, referencedColumnName = "group_id")
    private  Long groupId;

    @Column(name = "report_detail", nullable = false, columnDefinition = "TEXT")
    private  String reportDetail;

    @Column(name = "report_result", nullable = false)
    @Enumerated(EnumType.STRING)
    private ReportType reportType;

    @JoinColumn(name = "user_id", nullable = false)
    private String reportUser;

    @Column(name = "report_image")
    private  String reportImage;

    @Column(name = "report_result", nullable = false)
    @Enumerated(EnumType.STRING)
    private ReportResult reportResultf =  ReportResult.processing;


}
