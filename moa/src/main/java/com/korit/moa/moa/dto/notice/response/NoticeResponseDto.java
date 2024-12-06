package com.korit.moa.moa.dto.notice.response;

import com.korit.moa.moa.entity.notice.Notice;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class NoticeResponseDto {

    private Long noticeId;

    private String noticeTitle;

    private String noticeContent;

    private Date noticeDate;

    public NoticeResponseDto(Notice notice) {
        this.noticeId = notice.getNoticeId();
        this.noticeTitle = notice.getNoticeTitle();
        this.noticeContent = notice.getNoticeContent();
        this.noticeDate = notice.getNoticeDate();
    }
}
