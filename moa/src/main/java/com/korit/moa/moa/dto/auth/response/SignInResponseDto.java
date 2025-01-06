package com.korit.moa.moa.dto.auth.response;

import com.korit.moa.moa.entity.user.Gender;
import com.korit.moa.moa.entity.user.Region;
import com.korit.moa.moa.entity.user.User;
import com.korit.moa.moa.entity.user.UserHobbies;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.Set;

@Data
@AllArgsConstructor
public class SignInResponseDto {

    private String userId;

    private String password;

    private Date userBirthDate;

    private Gender userGender;

    private String userName;

    private String nickName;

    private Set<UserHobbies> hobbies;

    private String  profileImage;

    private Region region;

    private String token;

    private int exprTime;

    public SignInResponseDto(User user, String token, int exprTime) {
        this.userId = user.getUserId();
        this.password = user.getPassword();
        this.userBirthDate = user.getUserBirthDate();
        this.userGender = user.getUserGender();
        this.userName = user.getUserName();
        this.nickName = user.getNickName();
        this.hobbies = user.getHobbies();
        this.profileImage = user.getProfileImage();
        this.region = user.getRegion();
        this.token = token;
        this.exprTime = exprTime;
    }
}
