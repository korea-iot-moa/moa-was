package com.korit.moa.moa.service;

import com.korit.moa.moa.dto.ResponseDto;
import com.korit.moa.moa.dto.mail.SendMailRequestDto;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

public interface MailService {

    MimeMessage createMail(String mail, String token) throws MessagingException;

    ResponseDto<String> sendMessage(SendMailRequestDto dto) throws MessagingException;

    ResponseDto<String> verifyEmail(String token);
}
