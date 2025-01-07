package com.korit.moa.moa.dto.chat.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessageResponseDto {
    private Long id;
    private Long roomId;
    private String sender;
    private String message;
    private LocalDateTime timestamp;
}
