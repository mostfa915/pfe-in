package com.example.pfev2.controllers;
import com.example.pfev2.entites.Communication;
import com.example.pfev2.repositorys.CommunicationRepository;
import com.example.pfev2.repositorys.UtilisateurRepository;
import com.example.pfev2.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.List;

@RestController
@RequestMapping("/api/communications")
@CrossOrigin(origins = "http://localhost:4200") // Ajustez selon votre config Angular
public class CommunicationController {

    private final CommunicationRepository communicationRepository;
    private final NotificationService notificationService;
    private final UtilisateurRepository utilisateurRepository;

    public CommunicationController(CommunicationRepository communicationRepository,
                                   NotificationService notificationService, UtilisateurRepository utilisateurRepository) {
        this.communicationRepository = communicationRepository;
        this.notificationService = notificationService;
        this.utilisateurRepository = utilisateurRepository;
    }

    @GetMapping
    public ResponseEntity<List<Communication>> getAllCommunications() {
        return ResponseEntity.ok(communicationRepository.findAll());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Communication>> getUserCommunications(@PathVariable Long userId) {
        return ResponseEntity.ok(communicationRepository.findByReceiverId(userId));
    }

    @GetMapping("/unread/{userId}")
    public ResponseEntity<List<Communication>> getUnreadCommunications(@PathVariable Long userId) {
        return ResponseEntity.ok(communicationRepository.findByReceiverIdAndIsReadFalse(userId));
    }

    @PostMapping
    public ResponseEntity<Communication> createCommunication(@RequestBody Communication communication,@RequestParam Long id) {
System.out.println(id);
communication.setSender( utilisateurRepository.getById(id));
        Communication saved = communicationRepository.save(communication);

        notificationService.sendNotification(saved);
        return ResponseEntity.created(URI.create("/api/communications/" + saved.getId())).body(saved);
    }

    @PutMapping("/{id}/mark-as-read")
    public ResponseEntity<Void> markAsRead(@PathVariable Long id) {
        communicationRepository.findById(id).ifPresent(comm -> {
            comm.setRead(true);
            communicationRepository.save(comm);
        });
        return ResponseEntity.ok().build();
    }

    @GetMapping("/unread-count/{userId}")
    public ResponseEntity<Long> getUnreadCount(@PathVariable Long userId) {
        return ResponseEntity.ok(communicationRepository.countByReceiverIdAndIsReadFalse(userId));
    }
}