package com.example.academix.controller;

import com.example.academix.model.ChatMessage;
import com.example.academix.repository.ChatMessageRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.time.LocalDateTime;
import java.util.List;

@RestController
public class ChatController {

    private final ChatMessageRepository chatMessageRepository;
    private final SimpMessagingTemplate messagingTemplate;

    public ChatController(ChatMessageRepository chatMessageRepository, SimpMessagingTemplate messagingTemplate) {
        this.chatMessageRepository = chatMessageRepository;
        this.messagingTemplate = messagingTemplate;
    }

    @MessageMapping("/chat.sendMessage/{groupId}")
    @SendTo("/topic/group/{groupId}")
    public ChatMessage sendMessage(@DestinationVariable Long groupId, @Payload ChatMessage chatMessage) {
        chatMessage.setGroupId(groupId);
        if (chatMessage.getTimestamp() == null) {
            chatMessage.setTimestamp(LocalDateTime.now());
        }
        return chatMessageRepository.save(chatMessage);
    }

    @MessageMapping("/chat.addUser/{groupId}")
    @SendTo("/topic/group/{groupId}")
    public ChatMessage addUser(@DestinationVariable Long groupId, @Payload ChatMessage chatMessage) {
        chatMessage.setGroupId(groupId);
        chatMessage.setTimestamp(LocalDateTime.now());
        chatMessage.setType(ChatMessage.MessageType.JOIN);
        // Usually JOIN events don't strictly need persistence, but we can save it for log history.
        return chatMessageRepository.save(chatMessage);
    }

    @GetMapping("/api/chats/{groupId}/history")
    public ResponseEntity<List<ChatMessage>> getGroupChatHistory(@PathVariable Long groupId) {
        return ResponseEntity.ok(chatMessageRepository.findByGroupIdOrderByTimestampAsc(groupId));
    }

    @PutMapping("/api/chats/{messageId}/pin")
    public ResponseEntity<Void> togglePinMessage(@PathVariable Long messageId) {
        chatMessageRepository.findById(messageId).ifPresent(msg -> {
            msg.setPinned(!msg.isPinned());
            chatMessageRepository.save(msg);
            messagingTemplate.convertAndSend("/topic/group/" + msg.getGroupId(), msg);
        });
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/api/chats/{messageId}")
    public ResponseEntity<Void> deleteMessage(@PathVariable Long messageId) {
        chatMessageRepository.findById(messageId).ifPresent(msg -> {
            msg.setDeleted(true);
            msg.setContent("This message was deleted");
            chatMessageRepository.save(msg);
            messagingTemplate.convertAndSend("/topic/group/" + msg.getGroupId(), msg);
        });
        return ResponseEntity.ok().build();
    }
}
