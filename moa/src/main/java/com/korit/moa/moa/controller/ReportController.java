package com.korit.moa.moa.controller;

import com.korit.moa.moa.common.constant.ApiMappingPattern;
import com.korit.moa.moa.dto.ResponseDto;
import com.korit.moa.moa.dto.report.request.CreateReportRequestDto;
import com.korit.moa.moa.dto.report.request.DeleteReportRequestDto;
import com.korit.moa.moa.dto.report.request.PostReportRequestDto;
import com.korit.moa.moa.dto.report.response.ReportResponseDto;
import com.korit.moa.moa.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiMappingPattern.REPORT)
public class ReportController {

    private final ReportService reportService;
    private static final String GET_REPORT = "/{groupId}";
    private static final String DEL_REPORT = "/{reportId}";

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

    //신고 조회
    @GetMapping(GET_REPORT)
    public  ResponseEntity<ResponseDto<List<ReportResponseDto>>> getReport(
            @PathVariable Long groupId)
    {
        System.out.println("Group ID: " + groupId);
        ResponseDto<List<ReportResponseDto>> response = reportService.getReport(groupId);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }

    //신고 처리 - 추방
    @PostMapping(GET_REPORT)
    public  ResponseEntity<ResponseDto<Void>> postReport(
            @PathVariable Long groupId, @RequestBody PostReportRequestDto dto){
        System.out.println("Group ID: " + groupId);
        System.out.println("DTO Data: " + dto);
        ResponseDto<Void> response = reportService.postReport(groupId,dto);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }

    //신고 처리 - 유지
    @DeleteMapping(GET_REPORT)
    public  ResponseEntity<ResponseDto<Void>> deleteReport(
            @PathVariable Long groupId, @RequestBody DeleteReportRequestDto dto){
        ResponseDto<Void> response = reportService.deleteReport(groupId,dto);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }


}

















