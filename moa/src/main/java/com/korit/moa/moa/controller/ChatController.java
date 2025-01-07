package com.korit.moa.moa.controller;

import com.korit.moa.moa.common.constant.ApiMappingPattern;
import com.korit.moa.moa.dto.ResponseDto;
import com.korit.moa.moa.dto.chat.request.ChatMessageRequestDto;
import com.korit.moa.moa.dto.chat.response.ChatMessageResponseDto;
import com.korit.moa.moa.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(ApiMappingPattern.CHAT)
@RequiredArgsConstructor
public class ChatController {
    private final ChatService chatService;

    private static final String SEND_MESSAGE = "/chat/{roomId}";
    private static final String GET_CHAT_HISTORY = "/history/{roomId}";

    @MessageMapping(SEND_MESSAGE)
    public ResponseEntity<ResponseDto<Void>> sendMessage(@DestinationVariable Long roomId, ChatMessageRequestDto chatMessageDto) {
        chatMessageDto.setRoomId(roomId);
        ResponseDto<Void> response = chatService.publishMessage(chatMessageDto);
        return ResponseEntity.status(response.isResult() ? 200 : 400).body(response);
    }

    @GetMapping(GET_CHAT_HISTORY)
    public ResponseEntity<ResponseDto<List<ChatMessageResponseDto>>> getChatHistory(@PathVariable Long roomId) {
        ResponseDto<List<ChatMessageResponseDto>> response = chatService.getFormattedChatHistory(roomId);
        return ResponseEntity.status(response.isResult() ? 200 : 400).body(response);
    }
}
