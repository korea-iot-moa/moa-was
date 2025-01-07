package com.korit.moa.moa.repository;

import com.korit.moa.moa.entity.chat.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChatRepository extends JpaRepository<ChatMessage, Long> {
    @Query("SELECT cm FROM ChatMessage cm WHERE cm.chatRoom.chatRoomId = :roomId ORDER BY cm.createdAt ASC")
    List<ChatMessage> findByRoomIdOrderByTimestampAsc(@Param("roomId") Long roomId);
}

