package com.korit.moa.moa.dto.report.request;

import com.korit.moa.moa.entity.Report.ReportType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
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

    private MultipartFile reportImage;

}
