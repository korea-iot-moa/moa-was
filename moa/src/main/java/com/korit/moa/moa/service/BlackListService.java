package com.korit.moa.moa.service;

import com.korit.moa.moa.repository.BlackListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BlackListService {

    public final BlackListRepository blackListRepository;
}
