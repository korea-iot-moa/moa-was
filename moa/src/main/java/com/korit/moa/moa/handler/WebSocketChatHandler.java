package com.korit.moa.moa.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.korit.moa.moa.dto.chat.WebSocketChatMessageDto;
import com.korit.moa.moa.dto.chat.WebSocketMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Controller
@Log4j2
@RequiredArgsConstructor
public class WebSocketChatHandler extends TextWebSocketHandler {
    private final Map<Long, WebSocketChatRoom> chatRoomMap = new ConcurrentHashMap<>();
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final SimpMessagingTemplate messagingTemplate;

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String username = (String) session.getAttributes().get("username");
        WebSocketMessage webSocketMessage = objectMapper.readValue(message.getPayload(), WebSocketMessage.class);
        switch (webSocketMessage.getType()) {
            case ENTER -> enterChatRoom(webSocketMessage.getPayload(), session);
            case TALK -> sendMessage(username, webSocketMessage.getPayload());
            case EXIT -> exitChatRoom(username, webSocketMessage.getPayload());
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        String username = (String) session.getAttributes().get("username");
        chatRoomMap.values().forEach(chatRoom -> chatRoom.getActiveUserMap().remove(username));
        log.info("Connection closed: " + session.getId());
    }

    private void sendMessage(String username, WebSocketChatMessageDto chatDto) {
        log.info("Sending chat message: {}", chatDto);
        WebSocketChatRoom chatRoom = chatRoomMap.get(chatDto.getChatRoomId());
        chatRoom.sendMessage(username, chatDto);
        messagingTemplate.convertAndSend("/topic/" + chatDto.getChatRoomId(), chatDto);
    }

    private void enterChatRoom(WebSocketChatMessageDto chatDto, WebSocketSession session) {
        log.info("Entering chat room: {}", chatDto);
        chatDto.setMessage(chatDto.getNickname() + "님이 입장하셨습니다.");
        WebSocketChatRoom chatRoom = chatRoomMap.getOrDefault(chatDto.getChatRoomId(), new WebSocketChatRoom(objectMapper));
        chatRoom.enter(chatDto, session);
        chatRoomMap.put(chatDto.getChatRoomId(), chatRoom);
        messagingTemplate.convertAndSend("/topic/" + chatDto.getChatRoomId(), chatDto);
    }

    private void exitChatRoom(String username, WebSocketChatMessageDto chatDto) {
        log.info("Exiting chat room: {}", chatDto);
        chatDto.setMessage(chatDto.getNickname() + "님이 퇴장하셨습니다.");
        WebSocketChatRoom chatRoom = chatRoomMap.get(chatDto.getChatRoomId());
        chatRoom.exit(username, chatDto);
        if (chatRoom.getActiveUserMap().isEmpty()) {
            chatRoomMap.remove(chatDto.getChatRoomId());
        }
        messagingTemplate.convertAndSend("/topic/" + chatDto.getChatRoomId(), chatDto);
    }
}