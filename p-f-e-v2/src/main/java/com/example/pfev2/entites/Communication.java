package com.example.pfev2.entites;

// model/Communication.java

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
public class Communication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;
    private LocalDateTime dateCreation;
    private boolean isRead;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    private Utilisateur sender;

    @ManyToOne
    @JoinColumn(name = "receiver_id")
    private Utilisateur receiver;

    @Enumerated(EnumType.STRING)
    private CommunicationType type; // SMS, EMAIL, NOTIFICATION

    public Communication(String content, Utilisateur sender, Utilisateur receiver) {
        this.content = content;
        this.sender = sender;
        this.receiver = receiver;
        this.dateCreation = LocalDateTime.now();
        this.isRead = false;
    }

    public Communication() {
        this.dateCreation = LocalDateTime.now();
    }
}

