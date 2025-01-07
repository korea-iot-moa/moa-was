package com.korit.moa.moa.dto.chat;

import com.korit.moa.moa.entity.chat.WebSocketMessageType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WebSocketMessage {
    private WebSocketMessageType type; // 메시지 타입
    private WebSocketChatMessageDto payload; // 메시지 데이터
}