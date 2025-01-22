package com.korit.moa.moa.service;

import com.korit.moa.moa.dto.ResponseDto;
import com.korit.moa.moa.dto.chat.request.ChatMessageRequestDto;
import com.korit.moa.moa.dto.chat.response.ChatMessageResponseDto;

import java.util.List;

public interface ChatService {

    ResponseDto<Void> saveMessage(ChatMessageRequestDto dto);

    ResponseDto<Void> publishMessage(ChatMessageRequestDto dto);

    ResponseDto<List<ChatMessageResponseDto>> getFormattedChatHistory(Long roomId);
}