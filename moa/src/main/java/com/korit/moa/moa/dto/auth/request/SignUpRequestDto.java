package com.korit.moa.moa.dto.auth.request;

import com.korit.moa.moa.entity.user.Gender;
import com.korit.moa.moa.entity.user.Hobby;
import com.korit.moa.moa.entity.user.Region;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

@Data
@NoArgsConstructor
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
    private Date userBirthDate;

    private Set<Hobby> hobbies;

    private String profileImage ;

    private Region region;



}
