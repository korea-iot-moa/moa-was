package com.korit.moa.moa.controller;

import com.korit.moa.moa.common.constant.ApiMappingPattern;
import com.korit.moa.moa.dto.ResponseDto;
import com.korit.moa.moa.dto.mail.SendMailRequestDto;
import com.korit.moa.moa.service.MailService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiMappingPattern.MAIL)
public class MailController {

    private final MailService mailService;

    @PostMapping("/send")
    public ResponseEntity<ResponseDto<String>> sendEmail(@RequestBody SendMailRequestDto dto) throws MessagingException {
        ResponseDto<String> response = mailService.sendMessage(dto);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }

    @GetMapping("/verify")
    public ResponseEntity<ResponseDto<String>> verifyEmail(@RequestParam String token) {
        ResponseDto<String> response = mailService.verifyEmail(token);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }
}
