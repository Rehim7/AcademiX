package com.example.academix.model;

import java.time.LocalDateTime;

public class ChatMessage {
    private MessageType type;
    private LocalDateTime timestamp;
    private String content;
    private String sender;

    public enum MessageType {
        CHAT,
        JOIN,
        LEAVE
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }
}
