package com.korit.moa.moa.dto.chat;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class WebSocketChatMessageDto {
    @NotNull
    private Long chatRoomId;  // 채팅방 ID

    @NotEmpty
    private String nickname;  // 사용자 닉네임

    @NotEmpty
    private String message;   // 메시지 내용

    // 사용자 프로필
}
