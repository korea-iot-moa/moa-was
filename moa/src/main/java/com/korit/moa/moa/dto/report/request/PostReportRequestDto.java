package com.korit.moa.moa.dto.report.request;

import com.korit.moa.moa.entity.Report.ReportResult;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PostReportRequestDto {

    @NotBlank
    private String reportUser;

    @NotBlank
    private ReportResult reportResult;


}
