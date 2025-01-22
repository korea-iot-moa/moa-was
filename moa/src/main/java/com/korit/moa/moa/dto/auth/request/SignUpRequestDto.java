package com.korit.moa.moa.dto.auth.request;

import com.korit.moa.moa.entity.user.Gender;
import com.korit.moa.moa.entity.user.Region;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequestDto {

    @NotBlank
    private String userId;

    @NotBlank
    private String password;

    @NotBlank
    private String confirmPassword;

    @NotBlank
    private String userName;

    @NotBlank
    private String nickName;

    @NotBlank
    private Gender userGender;

    @NotBlank
    private String userBirthDate;

    private Set<Long> hobbies;

    private MultipartFile profileImage ;

    private Region region;

    @NotBlank
    @Pattern(regexp="^(home|kakao|naver)$")
    private String joinPath;
    private String snsId;

    @NotBlank
    private String phoneNumber;

    @NotBlank
    private String email;
}