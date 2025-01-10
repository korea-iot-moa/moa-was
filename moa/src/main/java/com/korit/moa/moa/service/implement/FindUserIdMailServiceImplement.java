package com.korit.moa.moa.service.implement;

import com.korit.moa.moa.common.constant.ResponseMessage;
import com.korit.moa.moa.dto.ResponseDto;
import com.korit.moa.moa.dto.mail.FindUserIdMailRequestDto;
import com.korit.moa.moa.entity.user.User;
import com.korit.moa.moa.provider.JwtProvider;
import com.korit.moa.moa.repository.UserRepository;
import com.korit.moa.moa.service.FindUserIdMailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class FindUserIdMailServiceImplement implements FindUserIdMailService {

    private final JavaMailSender javaMailSender;
    private final JwtProvider jwtProvider;
    private final UserRepository userRepository;

    @Value("${spring.mail.username}")
    private static String senderEmail;

    // 메일 내용 생성
    @Override
    public MimeMessage createMail(String mail, String token) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        message.setFrom(senderEmail); // 발신자 설정
        message.setRecipients(MimeMessage.RecipientType.TO, mail); // 수신자 설정
        message.setSubject("MOA 아이디 이메일 인증");//이메일 제목

        String body = "";
        body += "<h2> 이메일 인증 링크 입니다.</h2>";
        body += "<a href=\"http://localhost:3000/findUserId/verify-find-userId?token" + token + "\">여기를 클릭해 인중해주세요.</a>";
        body += "<p>이용해주셔서 감사합니다.</p>";

        message.setText(body, "UTF-8", "html");

        return message;
    }

    // 메일 보내기
    @Override
    public ResponseDto<String> sendMessage(FindUserIdMailRequestDto dto) throws MessagingException {
//        String mobileNumber = dto.getMobileNumber();
//        String userName = dto.getUserName();
//        String mail = dto.getEmail();
//
//        try {
//            boolean userExists = userRepository.existsByUserNameAndMobileNumber(userName, mobileNumber);
//            System.out.println(userExists);
//            if (!userExists) {
//                return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_DATA);
//            }
//
//            String token = jwtProvider.generateEmailValidTokenFindUserId(userName);
//
//            MimeMessage message = createMail(mail, token);
//            try{
//                javaMailSender.send(message);
//                return ResponseDto.setSuccess(ResponseMessage.MESSAGE_TOKEN_SUCCESS, token);
//            } catch (MailException e) {
//                e.printStackTrace();
//                return ResponseDto.setFailed(ResponseMessage.MESSAGE_SEND_FAIL);
//            }
//
//        } catch (MailException e) {
//            e.printStackTrace();
//            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
//        }
        return null;
    }


    @Override
    public ResponseDto<String> verityEmail(String token) {
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, token);
    }
}
