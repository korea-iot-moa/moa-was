package com.korit.moa.moa.service;

import com.korit.moa.moa.repository.MeetingGroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MeetingGroupService {

    public final MeetingGroupRepository meetingGroupRepository;

}
