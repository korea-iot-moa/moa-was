package com.korit.moa.moa.entity.balckList;

import com.korit.moa.moa.entity.meetingGroup.MeetingGroup;
import com.korit.moa.moa.entity.user.User;
import jakarta.persistence.*;
import lombok.*;


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


    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    @ManyToOne
    @MapsId("groupId")
    @JoinColumn(name = "group_id", referencedColumnName = "group_id")
    private MeetingGroup group;
}
