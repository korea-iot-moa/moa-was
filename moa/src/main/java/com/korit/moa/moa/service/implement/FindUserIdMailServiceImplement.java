package com.korit.moa.moa.service.implement;

import com.korit.moa.moa.common.constant.ResponseMessage;
import com.korit.moa.moa.dto.ResponseDto;
import com.korit.moa.moa.dto.auth.response.FindIdResponseDto;
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
import java.util.Optional;

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
        body += "<a href=\"http://localhost:3000/findUserId/verify-find-userId?token=" + token + "\">여기를 클릭해 인중해주세요.</a>";
        body += "<p>이용해주셔서 감사합니다.</p>";

        message.setText(body, "UTF-8", "html");

        return message;
    }

    // 메일 보내기
    @Override
    public ResponseDto<String> sendMessage(FindUserIdMailRequestDto dto) throws MessagingException {
        String phoneNumber = dto.getPhoneNumber();
        String userName = dto.getUserName();

        try {
            Optional<User> userData = userRepository.findByUserNameAndUserBirthDate(userName, phoneNumber);
            System.out.println(userData);

            if (userData.isEmpty()) {
                return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_DATA);
            }

            User user = userData.get();

            String token = jwtProvider.generateEmailValidTokenFindId(userName, phoneNumber);

            MimeMessage message = createMail(user.getEmail(), token);
            try{
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
    public ResponseDto<FindIdResponseDto> verifyEmail(String token) {
        FindIdResponseDto data = null;

        String userName = jwtProvider.getUserNameFromJwt(token);
        String phoneNumber = jwtProvider.getPhoneNumberFromJwt(token);

        try{
            Optional<User> userOptional = userRepository.findByUserNameAndUserBirthDate(userName, phoneNumber);

            if (userOptional.isEmpty()) {
                return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_DATA);
            }
            User user = userOptional.get();
            data = new FindIdResponseDto(user);

        } catch (Exception e){
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }
}
