package com.korit.moa.moa.service.implement;

import com.korit.moa.moa.repository.NoticeRepository;
import com.korit.moa.moa.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NoticeServiceImplement implements NoticeService {

    public final NoticeRepository noticeRepository;
}
