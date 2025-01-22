package com.korit.moa.moa.service;

import com.korit.moa.moa.dto.ResponseDto;
import com.korit.moa.moa.dto.report.request.CreateReportRequestDto;
import com.korit.moa.moa.dto.report.request.DeleteReportRequestDto;
import com.korit.moa.moa.dto.report.request.PostReportRequestDto;
import com.korit.moa.moa.dto.report.response.ReportResponseDto;

import java.util.List;

public interface ReportService {
    ResponseDto<ReportResponseDto> createReport (String userId, CreateReportRequestDto dto);

    ResponseDto<List<ReportResponseDto>> getReport(Long groupId);

    ResponseDto<Void> deleteReport(Long groupId, DeleteReportRequestDto dto);

    ResponseDto<Void> postReport(Long groupId, PostReportRequestDto dto);
}
