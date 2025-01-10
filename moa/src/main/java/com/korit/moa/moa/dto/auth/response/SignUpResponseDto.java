package com.korit.moa.moa.dto.auth.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.korit.moa.moa.entity.user.Gender;
import com.korit.moa.moa.entity.user.Region;
import com.korit.moa.moa.entity.user.User;
import com.korit.moa.moa.entity.user.UserHobbies;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SignUpResponseDto {

    private String userId;

    private String password;

    private Date userBirthDate;

    private Gender userGender;

    private String userName;

    private String nickName;

    private Set<UserHobbies> hobbies;

    private String  profileImage;

    private Region region;

    private String joinPath;

    private String snsId;

    private String phoneNumber;

    private String email;

    public SignUpResponseDto(User user) {
        this.userId = user.getUserId();
        this.password = user.getPassword();
        this.userBirthDate = user.getUserBirthDate();
        this.userGender = user.getUserGender();
        this.userName = user.getUserName();
        this.nickName = user.getNickName();
        this.profileImage = user.getProfileImage();
        this.hobbies = user.getHobbies();
        this.region = user.getRegion();
        this.joinPath = user.getJoinPath();
        this.snsId = user.getSnsId();
        this.phoneNumber = user.getPhoneNumber();
        this.email = user.getEmail();
    }
}