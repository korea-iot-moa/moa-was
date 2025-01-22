package com.korit.moa.moa.service.implement;

import com.korit.moa.moa.common.constant.ResponseMessage;
import com.korit.moa.moa.dto.ResponseDto;
import com.korit.moa.moa.dto.mail.SendMailRequestDto;
import com.korit.moa.moa.entity.user.User;
import com.korit.moa.moa.provider.JwtProvider;
import com.korit.moa.moa.repository.UserRepository;
import com.korit.moa.moa.service.MailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MailServiceImplement implements MailService {

    private final JavaMailSender javaMailSender;
    private final JwtProvider jwtProvider;
    private final UserRepository userRepository;

    @Value("${spring.mail.username}")
    private static String senderEmail;

    @Override
    public MimeMessage createMail(String mail, String token) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        message.setFrom(senderEmail);
        message.setRecipients(MimeMessage.RecipientType.TO, mail);
        message.setSubject("MOA 이메일 인증");

        String body = "";
        body += "<h2> 이메일 인증 링크 입니다 </h2>";
        body += "<a href=\"http://localhost:3000/findPassword/verify?token=" + token + "\"> 여기를 클릭하여 인증 </a>";
        body += "<p> 감사합니다. <p>";

        message.setText(body, "UTF-8", "html");

        return message;
    }

    @Override
    public ResponseDto<String> sendMessage(SendMailRequestDto dto) throws MessagingException {

        try {
            Optional<User> userResult = userRepository.findByUserIdAndUserName(
                    dto.getUserId(),
                    dto.getUserName()
            );

            User user = userResult.get();

            if (userResult.isEmpty()) {
                return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_DATA);
            }

            String token = jwtProvider.generateEmailValidToken(dto.getUserId());

            MimeMessage message = createMail(user.getEmail(), token);
            try {
                javaMailSender.send(message);
                return ResponseDto.setSuccess(ResponseMessage.MESSAGE_TOKEN_SUCCESS, token);
            } catch (MailException e) {
                e.printStackTrace();
                return ResponseDto.setFailed(ResponseMessage.MESSAGE_SEND_FAIL);
            }

        } catch (MailException e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
    }


    @Override
    public ResponseDto<String> verifyEmail(String token) {
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, token);
    }
}
