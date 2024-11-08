package com.korit.moa.moa.entity.userList;

import com.korit.moa.moa.entity.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "User_List")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserList {
    @Id
    @JoinColumn(name = "group_id", nullable = false, unique = true)
    @ManyToOne
    private MeetingGroup groupId;

    @Id
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    @ManyToOne
    private User userId;

    @Column(name = "user_level", nullable = false, columnDefinition = "ENUM('일반회원', '우수회원', '관리자') DEFAULT '일반회원'")
    private UserLevel userLevel;

    @Column(name = "join_date", nullable = false)
    private Date joinDate;
}
