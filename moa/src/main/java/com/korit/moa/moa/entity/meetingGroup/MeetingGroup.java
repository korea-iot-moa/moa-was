package com.korit.moa.moa.entity.meetingGroup;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Meeting_Groups")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MeetingGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "group_id")
    private Long groupId;

    @Column(name = "creator_id")
    private String creatorId;

    @Column(nullable = false, length = 255, name = "group_title")
    private String groupTitle;

    @Column(nullable = false, name = "group_content")
    private String groupContent;

    @Column(nullable = false, length = 255, name = "group_address")
    private String groupAddress;

    @Column(name = "group_image")
    private String  groupImage;

    @Column(nullable = false, length = 255, name = "group_supplies")
    private String groupSupplies;

    @Column(nullable = false, name = "group_date")
    private String groupDate;

    @Column(nullable = false, name = "group_question")
    private String group_question;

    @Enumerated(EnumType.STRING)
    @Column(name = "group_category")
    private GroupCategory groupCategory;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "group_type")
    private GroupTypeCategory groupType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "meeting_type")
    private MeetingTypeCategory meetingType;
}
