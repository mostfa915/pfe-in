package com.example.pfev2.services;
// NotificationService.java

import com.example.pfev2.entites.Communication;
import com.example.pfev2.entites.NotificationMessage;

import com.example.pfev2.repositorys.CommunicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationService {
    private final WebSocketService webSocketService;
    private final CommunicationRepository communicationRepository;

    public void sendNotification(Communication communication) {
        Communication savedComm = communicationRepository.save(communication);
        webSocketService.sendToUser(
                "1",
                "/queue/notifications",
                new NotificationMessage(savedComm)
        );
    }
}