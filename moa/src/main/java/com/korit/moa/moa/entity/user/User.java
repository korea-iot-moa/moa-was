package com.korit.moa.moa.entity.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Users")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @Column(name = "user_id", updatable = false)
    private String userId;

    @Column(name = "user_password", nullable = false)
    private String password;

    @Past
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    @Column(name = "user_birth_date", nullable = false)
    private Date userBirthDate;

    @Column(name = "user_gender")
    @Enumerated(EnumType.STRING)
    private Gender userGender;

    @Column(name = "user_name", nullable = false)
    private String userName;

    @Column(name = "user_nickname", nullable = false, unique = true)
    private String nickName;

    @Column(name = "hobby")
    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<Hobby> hobbies = new HashSet<>();

    @Column(name = "profile_image")
    private String profileImage;

    @Column(name = "region")
    @Enumerated(EnumType.STRING)
    private Region region;
}
