package com.korit.moa.moa.service;

import com.korit.moa.moa.dto.ResponseDto;
import com.korit.moa.moa.dto.mail.FindUserIdMailRequestDto;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

public interface FindUserIdMailService {
    MimeMessage createMail(String mail, String token) throws MessagingException;
    ResponseDto<String> sendMessage(FindUserIdMailRequestDto dto) throws MessagingException;
    ResponseDto<String> verityEmail(String token);
}
