package com.korit.moa.moa.entity.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.korit.moa.moa.entity.recommendation.Recommendation;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.*;

@Entity
@Table(name = "users")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @Column(name = "user_id", updatable = false)
    private String userId;

    @Column(name = "user_password", nullable = false)
    @JsonIgnoreProperties
    private String password;

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

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<UserHobbies> hobbies = new HashSet<>();

    @Column(name = "profile_image")
    private String profileImage;

    @Column(name = "region")
    @Enumerated(EnumType.STRING)
    private Region region;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Recommendation> recommendation = new ArrayList<>();

    @Column(nullable = false, length = 5, columnDefinition = "VARCHAR(5) COMMENT '가입 경로 (HOME, KAKAO, NAVER)'")
    private String joinPath;

    @Column(nullable = true, columnDefinition = "VARCHAR(255) COMMENT 'OAuth2 사용자 아이디'")
    private String snsId;

    @PrePersist
    private void setDefaultValues() {
        if (this.joinPath == null) {
            this.joinPath = "HOME"; // 기본값 설정
        }
    }

    @Column(name = "user_phone_number",nullable = false)
    private String phoneNumber;

    @Column(name = "user_mail" , nullable = false)
    private String email;
}