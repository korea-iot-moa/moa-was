package com.korit.moa.moa.service;

import com.korit.moa.moa.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NoticeService  {

    public final NoticeRepository noticeRepository;
}
