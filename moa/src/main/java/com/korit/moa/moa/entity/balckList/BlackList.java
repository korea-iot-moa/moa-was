package com.korit.moa.moa.entity.balckList;

import com.korit.moa.moa.entity.meetingGroup.MeetingGroup;
import com.korit.moa.moa.entity.user.User;
import jakarta.persistence.*;
import lombok.*;
import com.korit.moa.moa.entity.meetingGroup.MeetingGroup;

@Entity
@Table(name = "Black_List")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BlackList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "black_list_id")
    private Long blackListId;

    @Column(name = "user_id")
    private String user;

    @Column(nullable = false, name = "group_id")
    private Long groupId;
}
