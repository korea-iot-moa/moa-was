package com.korit.moa.moa.service.implement;

import com.korit.moa.moa.common.constant.ResponseMessage;
import com.korit.moa.moa.dto.ResponseDto;
import com.korit.moa.moa.dto.notice.response.NoticeResponseDto;
import com.korit.moa.moa.entity.notice.Notice;
import com.korit.moa.moa.repository.NoticeRepository;
import com.korit.moa.moa.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NoticeServiceImplement implements NoticeService {

    public final NoticeRepository noticeRepository;

    @Override
    public ResponseDto<List<NoticeResponseDto>> getAllNotice() {
        List<NoticeResponseDto> data = null;

        try{
            List<Notice> notice = noticeRepository.findAll();

            data = notice.stream()
                    .map(NoticeResponseDto :: new)
                    .collect(Collectors.toList());

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }
}
