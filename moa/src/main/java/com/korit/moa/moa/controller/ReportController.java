package com.korit.moa.moa.controller;

import com.korit.moa.moa.common.constant.ApiMappingPattern;
import com.korit.moa.moa.dto.ResponseDto;
import com.korit.moa.moa.dto.report.request.CreateReportRequestDto;
import com.korit.moa.moa.dto.report.response.ReportResponseDto;
import com.korit.moa.moa.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiMappingPattern.REPORT)
public class ReportController {

    private final ReportService reportService;

    // 신고 등록
    @PostMapping
    public ResponseEntity<ResponseDto<ReportResponseDto>> createReport(
            @AuthenticationPrincipal String userId,
            @RequestBody CreateReportRequestDto dto
    ) {
        ResponseDto<ReportResponseDto> response = reportService.createReport(userId, dto);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }
}

















