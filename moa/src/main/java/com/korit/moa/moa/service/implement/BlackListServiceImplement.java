package com.korit.moa.moa.service.implement;

import com.korit.moa.moa.repository.BlackListRepository;
import com.korit.moa.moa.service.BlackListService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BlackListServiceImplement implements BlackListService {

    public final BlackListRepository blackListRepository;
}
