package com.korit.moa.moa.service;

import com.korit.moa.moa.dto.ResponseDto;
import com.korit.moa.moa.dto.report.request.CreateReportRequestDto;
import com.korit.moa.moa.dto.report.request.DeleteReportRequestDto;
import com.korit.moa.moa.dto.report.response.ReportResponseDto;

import java.util.List;

public interface ReportService {
    // 신고 작성
    ResponseDto<ReportResponseDto> createReport (String userId, CreateReportRequestDto dto);

    //신고 조회
    ResponseDto<List<ReportResponseDto>> getReport(Long groupId);

    //신고 처리 - 유지
    ResponseDto<Void> deleteReport(Long groupId, DeleteReportRequestDto dto);

    //신고 처리 - 추방
    ResponseDto<Void> postReport(Long groupId, DeleteReportRequestDto dto);
}
