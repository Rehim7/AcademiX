package com.example.academix.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
public class ChatMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private MessageType type;

    private LocalDateTime timestamp;
    private String content;
    private String sender;       // legacy field
    private String senderName;   // full name shown in chat
    private String senderEmail;  // used for ownership check
    private Long groupId;

    private boolean isPinned = false;
    private boolean isDeleted = false;

    public enum MessageType {
        CHAT,
        JOIN,
        LEAVE
    }
}
