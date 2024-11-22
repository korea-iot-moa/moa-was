package com.korit.moa.moa.dto.report.response;

import com.korit.moa.moa.entity.Report.Report;
import com.korit.moa.moa.entity.Report.ReportResult;
import com.korit.moa.moa.entity.Report.ReportType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReportResponseDto {

    private Long reportId;

    private String userId;

    private Long groupId;

    private String reportDetail;

    private ReportType reportType;

    private String reportUser;

    private String reportImage;

    private ReportResult reportResult;

    public ReportResponseDto(Report report) {
        this.reportId = report.getReportId();
        this.groupId = report.getGroupId();
        this.userId = report.getUserId();
        this.reportDetail = report.getReportDetail();
        this.reportType = report.getReportType();
        this.reportUser = report.getReportUser();
        this.reportImage = report.getReportImage();
        this.reportResult = report.getReportResult();
    }
}
