package com.korit.moa.moa.service.implement;

import com.korit.moa.moa.dto.ResponseDto;
import com.korit.moa.moa.dto.chat.request.ChatMessageRequestDto;
import com.korit.moa.moa.dto.chat.response.ChatMessageResponseDto;
import com.korit.moa.moa.entity.chat.ChatMessage;
import com.korit.moa.moa.entity.chat.ChatRoom;
import com.korit.moa.moa.repository.ChatRepository;
import com.korit.moa.moa.service.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class ChatServiceImpl implements ChatService {

    private final ChatRepository chatRepository;
    private final RedisTemplate<String, Object> redisTemplate;

    @Override
    public ResponseDto<Void> saveMessage(ChatMessageRequestDto dto) {
        try {
            ChatMessage message = ChatMessage.builder()
                    .chatRoom(ChatRoom.builder().chatRoomId(dto.getRoomId()).build())
                    .userId(dto.getSender())
                    .messageType(ChatMessage.MessageType.TALK)
                    .content(dto.getMessage())
                    .createdAt(LocalDateTime.now())
                    .build();
            chatRepository.save(message);
        } catch (Exception e) {
            log.error("Failed to save message: {}", e.getMessage());
            return ResponseDto.setFailed("DATABASE_ERROR");
        }
        return ResponseDto.setSuccess("SUCCESS", null);
    }

    @Override
    public ResponseDto<Void> publishMessage(ChatMessageRequestDto dto) {
        try {
            saveMessage(dto);
            redisTemplate.convertAndSend("chatroom:" + dto.getRoomId(), dto);
            return ResponseDto.setSuccess("SUCCESS", null);
        } catch (Exception e) {
            log.error("Redis publish error: {}", e.getMessage());
            return ResponseDto.setFailed("REDIS_ERROR");
        }
    }

    @Override
    public ResponseDto<List<ChatMessageResponseDto>> getFormattedChatHistory(Long roomId) {
        try {
            List<ChatMessage> history = chatRepository.findByRoomIdOrderByTimestampAsc(roomId);
            List<ChatMessageResponseDto> data = history.stream()
                    .map(msg -> new ChatMessageResponseDto(
                            msg.getMessageId(),
                            msg.getChatRoom().getChatRoomId(),
                            msg.getUserId(),
                            msg.getContent(),
                            msg.getCreatedAt()))
                    .collect(Collectors.toList());
            return ResponseDto.setSuccess("SUCCESS", data);
        } catch (Exception e) {
            log.error("Failed to fetch chat history: {}", e.getMessage());
            return ResponseDto.setFailed("DATABASE_ERROR");
        }
    }
}
