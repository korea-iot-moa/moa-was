package com.korit.moa.moa.controller;

import com.korit.moa.moa.common.constant.ApiMappingPattern;
import com.korit.moa.moa.dto.ResponseDto;
import com.korit.moa.moa.dto.notice.response.NoticeResponseDto;
import com.korit.moa.moa.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiMappingPattern.NOTICE)
public class NoticeController {

    private final NoticeService noticeService;

    // 공지사항 전체 조회
    @GetMapping
    public ResponseEntity<ResponseDto<List<NoticeResponseDto>>> getAllNotice() {
        ResponseDto<List<NoticeResponseDto>> response = noticeService.getAllNotice();
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }
}
