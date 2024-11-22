package com.korit.moa.moa.service.implement;

import com.korit.moa.moa.common.constant.ResponseMessage;
import com.korit.moa.moa.dto.ResponseDto;
import com.korit.moa.moa.dto.report.request.CreateReportRequestDto;
import com.korit.moa.moa.dto.report.response.ReportResponseDto;
import com.korit.moa.moa.entity.Report.Report;
import com.korit.moa.moa.entity.Report.ReportResult;
import com.korit.moa.moa.entity.Report.ReportType;
import com.korit.moa.moa.repository.ReportRepository;
import com.korit.moa.moa.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReportServiceImplement implements ReportService {

    private final ReportRepository reportRepository;

    @Override
    public ResponseDto<ReportResponseDto> createReport(String userId, CreateReportRequestDto dto) {
        ReportResponseDto data = null;
        Long groupId = dto.getGroupId();
        String reportDetail = dto.getReportDetail();
        ReportType reportType = dto.getReportType();
        String reportUser = dto.getReportUser();
        String reportImage = dto.getReportImage();

        try{
            Report report = Report.builder()
                    .groupId(groupId)
                    .userId(userId)
                    .reportDetail(reportDetail)
                    .reportType(reportType)
                    .reportUser(reportUser)
                    .reportImage(reportImage)
                    .reportResult(ReportResult.처리중)
                    .build();
            reportRepository.save(report);

            data = new ReportResponseDto(report);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }
}
