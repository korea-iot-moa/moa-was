package com.korit.moa.moa.entity.userList;

import com.korit.moa.moa.entity.meetingGroup.MeetingGroup;
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

    @EmbeddedId
    private UserListId id;

    @ManyToOne
    @MapsId("groupId")
    @JoinColumn(name = "group_id", referencedColumnName = "group_id")
    private MeetingGroup group;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    @Column(name = "user_level", nullable = false, columnDefinition = "ENUM('일반회원', '우수회원', '관리자') DEFAULT '일반회원'")
    private UserLevel userLevel;

    @Column(name = "join_date", nullable = false)
    private Date joinDate;
}
