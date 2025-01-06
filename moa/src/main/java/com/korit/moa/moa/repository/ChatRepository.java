package com.korit.moa.moa.repository;

import com.korit.moa.moa.entity.chat.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatRepository extends JpaRepository<ChatMessage, Long> {
    List<ChatMessage> findByRoomIdOrderByTimestampAsc(Long roomId);
}

