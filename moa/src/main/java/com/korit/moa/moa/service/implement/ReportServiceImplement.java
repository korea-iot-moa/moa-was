package com.korit.moa.moa.service.implement;

import com.korit.moa.moa.common.constant.ResponseMessage;
import com.korit.moa.moa.dto.ResponseDto;
import com.korit.moa.moa.dto.report.request.CreateReportRequestDto;

import com.korit.moa.moa.dto.report.request.DeleteReportRequestDto;
import com.korit.moa.moa.dto.report.response.ReportResponseDto;
import com.korit.moa.moa.entity.Report.Report;
import com.korit.moa.moa.entity.Report.ReportResult;
import com.korit.moa.moa.entity.Report.ReportType;
import com.korit.moa.moa.entity.balckList.BlackList;
import com.korit.moa.moa.entity.meetingGroup.MeetingGroup;
import com.korit.moa.moa.entity.user.User;
import com.korit.moa.moa.repository.BlackListRepository;
import com.korit.moa.moa.repository.MeetingGroupRepository;
import com.korit.moa.moa.repository.ReportRepository;
import com.korit.moa.moa.repository.UserRepository;
import com.korit.moa.moa.service.ReportService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReportServiceImplement implements ReportService {

    private final ReportRepository reportRepository;
    private final MeetingGroupRepository meetingGroupRepository;
    private final UserRepository userRepository;
    private final BlackListRepository blackListRepository;

    @Override
    public ResponseDto<ReportResponseDto> createReport(String userId, CreateReportRequestDto dto) {
        ReportResponseDto data = null;
        Long groupId = dto.getGroupId();
        String reportDetail = dto.getReportDetail();
        ReportType reportType = dto.getReportType();
        String reportUser = dto.getReportUser();
        String reportImage = dto.getReportImage();

        try{
            Report report = Report.builder()
                    .groupId(groupId)
                    .userId(userId)
                    .reportDetail(reportDetail)
                    .reportType(reportType)
                    .reportUser(reportUser)
                    .reportImage(reportImage)
                    .reportResult(ReportResult.처리중)
                    .build();
            reportRepository.save(report);

            data = new ReportResponseDto(report);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    @Override
    //신고 조회
    public ResponseDto<List<ReportResponseDto>> getReport(Long groupId) {

        List<ReportResponseDto> data = null;

        if(groupId == null){
            return ResponseDto.setFailed(ResponseMessage.VALIDATION_FAIL);
        }
        try{
            List<Object[]> results = reportRepository.findReportByGroupId(groupId);
            data = results.stream()
                    .map(ressult -> {
                        Report report = (Report) ressult[0];
                        return new ReportResponseDto(report);
                    })
                    .toList();
            return ResponseDto.setSuccess(ResponseMessage.SUCCESS,data);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
    }

    //신고 삭제 - 블랙 리스트 등록
    //신고 유지시 - 그냥 삭제
    @Override
    @Transactional(rollbackOn = Exception.class)
    public ResponseDto<Void> deleteReport(Long groupId, DeleteReportRequestDto dto ) {
        ReportResult reportResult = dto.getReportResult();

        try {
            Optional<Report> optionalReport = reportRepository.findById(groupId);

            if (optionalReport.isEmpty()) {
                return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_GROUP);
            }
            if (reportResult == ReportResult.추방) {
                Optional<MeetingGroup> meetingGroupOptional = meetingGroupRepository.findById(groupId);
                Optional<User> userOptional = userRepository.findByUserId(dto.getUserId());

                if (meetingGroupOptional.isPresent() && userOptional.isPresent()) {
                    MeetingGroup meetingGroup = meetingGroupOptional.get();
                    User user = userOptional.get();
                    BlackList blackList = BlackList.builder()
                            .userId(user.getUserId())
                            .groupId(meetingGroup.getGroupId())
                            .build();
                    blackListRepository.save(blackList);
                }
            }
            if (reportResult == ReportResult.유지) {
                reportRepository.deleteByUserId(dto.getUserId());
                return ResponseDto.setSuccess(ResponseMessage.SUCCESS, null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, null);
    }

}
