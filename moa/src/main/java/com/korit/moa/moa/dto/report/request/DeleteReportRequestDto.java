package com.korit.moa.moa.dto.report.request;

import com.korit.moa.moa.entity.Report.ReportResult;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DeleteReportRequestDto {

    @NotBlank
    String userId;

    @NotBlank
    ReportResult reportResult;

}
