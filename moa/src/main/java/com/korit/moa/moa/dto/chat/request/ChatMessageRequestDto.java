package com.korit.moa.moa.dto.chat.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessageRequestDto {
    @NotNull
    private Long roomId;

    @NotEmpty
    private String sender;

    @NotEmpty
    private String message;
}