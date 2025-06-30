package com.example.pfev2.entites;

  // Update with your actual package

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class NotificationMessage {
    private Long communicationId;
    private String content;
    private String senderName;
    private LocalDateTime timestamp;
    private boolean isRead;
    private CommunicationType type;

    public NotificationMessage(Communication communication) {
        this.communicationId = communication.getId();
        this.content = communication.getContent();
        this.senderName = communication.getSender() != null ? communication.getSender().getEmail() : "System";
        this.timestamp = communication.getDateCreation() != null ? communication.getDateCreation() : LocalDateTime.now();
        this.isRead = communication.isRead();
        this.type = communication.getType();
    }
}