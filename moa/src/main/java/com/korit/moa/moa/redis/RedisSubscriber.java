package com.korit.moa.moa.redis;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.korit.moa.moa.dto.chat.request.ChatMessageRequestDto;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisSubscriber implements MessageListener {

    private final SimpMessagingTemplate messagingTemplate;
    private final ObjectMapper objectMapper;

    public RedisSubscriber(SimpMessagingTemplate messagingTemplate, ObjectMapper objectMapper) {
        this.messagingTemplate = messagingTemplate;
        this.objectMapper = objectMapper;
    }

    @Override
    public void onMessage(Message message, byte[] pattern) {
        try {
            // Redis 메시지를 ChatMessageRequestDto로 변환
            String msgBody = new String(message.getBody());
            ChatMessageRequestDto chatMessage = objectMapper.readValue(msgBody, ChatMessageRequestDto.class);

            System.out.println("Redis 메시지 수신: " + chatMessage);

            // WebSocket 클라이언트로 메시지 브로드캐스트
            messagingTemplate.convertAndSend("/topic/" + chatMessage.getRoomId(), chatMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
