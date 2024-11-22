package com.korit.moa.moa.dto.report.request;

import com.korit.moa.moa.entity.Report.ReportType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateReportRequestDto {

    @NotBlank
    private String userId;

    @NotNull
    private Long groupId;

    @NotBlank
    private String reportDetail;

    @NotBlank
    private ReportType reportType;

    @NotBlank
    private String reportUser;

    private String reportImage;

}
