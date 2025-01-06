package com.korit.moa.moa.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.korit.moa.moa.dto.chat.WebSocketChatMessageDto;
import com.korit.moa.moa.dto.chat.WebSocketMessage;
import com.korit.moa.moa.entity.chat.WebSocketMessageType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Log4j2
@Getter
@RequiredArgsConstructor
public class WebSocketChatRoom {
    private final Map<String, WebSocketSession> activeUserMap = new ConcurrentHashMap<>();
    private final ObjectMapper objectMapper;

    public void enter(WebSocketChatMessageDto chatDto, WebSocketSession session) {
        String username = (String) session.getAttributes().get("username");
        activeUserMap.put(username, session);
        broadcastMessage(WebSocketMessageType.ENTER, chatDto, username);
    }

    public void exit(String username, WebSocketChatMessageDto chatDto) {
        activeUserMap.remove(username);
        broadcastMessage(WebSocketMessageType.EXIT, chatDto, username);
    }

    public void sendMessage(String username, WebSocketChatMessageDto chatDto) {
        broadcastMessage(WebSocketMessageType.TALK, chatDto, username);
    }

    private void broadcastMessage(WebSocketMessageType type, WebSocketChatMessageDto chatDto, String senderUsername) {
        activeUserMap.forEach((username, session) -> {
            try {
                if (!username.equals(senderUsername)) {
                    session.sendMessage(getTextMessage(type, chatDto));
                }
            } catch (Exception e) {
                log.error("Failed to send message: {}", e.getMessage());
            }
        });
    }

    private TextMessage getTextMessage(WebSocketMessageType type, WebSocketChatMessageDto chatDto) {
        try {
            return new TextMessage(objectMapper.writeValueAsString(new WebSocketMessage(type, chatDto)));
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}