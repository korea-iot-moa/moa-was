package com.korit.moa.moa.service;

import com.korit.moa.moa.dto.ResponseDto;
import com.korit.moa.moa.dto.report.request.CreateReportRequestDto;
import com.korit.moa.moa.dto.report.response.ReportResponseDto;

public interface ReportService {
    // 신고 작성
    ResponseDto<ReportResponseDto> createReport (String userId, CreateReportRequestDto dto);

}
