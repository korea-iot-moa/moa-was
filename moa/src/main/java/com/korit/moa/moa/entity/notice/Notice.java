package com.korit.moa.moa.entity.notice;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "Notices")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Notice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notice_id")
    private Long noticeId;

    @Column(nullable = false, name = "notice_title")
    private String noticeTitle;

    @Column(nullable = false, name = "notice_content")
    private String noticeContent;

    @Column(nullable = false, name = "notice_date")
    private LocalDateTime noticeDate;
}
