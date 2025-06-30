package com.example.pfev2.contollerstest;
/*
import com.example.pfev2.controllers.CommunicationController;
import com.example.pfev2.entites.Communication;
import com.example.pfev2.repositorys.CommunicationRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.List;

@WebMvcTest(CommunicationController.class)  // Test uniquement le contrôleur
public class CommunicationControllerTest {

    @Autowired
    private MockMvc mockMvc;  // Pour simuler les requêtes HTTP

    @MockBean
    private CommunicationRepository communicationRepository;  // Mock du repository

    @Test
    public void testGetAllCommunications() throws Exception {
        // 1. Préparation des données mockées
        Communication comm1 = new Communication("SMS");
        Communication comm2 = new Communication( "Email");
        List<Communication> communications = Arrays.asList(comm1, comm2);

        Mockito.when(communicationRepository.findAll()).thenReturn(communications);

        // 2. Exécution de la requête + vérifications
        mockMvc.perform(get("/api/communications"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].content").value("SMS"))
                .andExpect(jsonPath("$[1].content").value("Email"));
    }

    @Test
    public void testGetCommunicationById() throws Exception {
        Communication comm = new Communication( "SMS");
        Mockito.when(communicationRepository.findById(1L)).thenReturn(comm);

        mockMvc.perform(get("/api/communications/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").value("SMS"));
    }

    @Test
    public void testCreateCommunication() throws Exception {
        Communication newComm = new Communication( "Push Notification");
        Mockito.when(communicationRepository.save(Mockito.any(Communication.class))).thenReturn(newComm);

        mockMvc.perform(post("/api/communications")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"content\": \"Push Notification\"}"))  // Body de la requête
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").value("Push Notification"));
    }

    @Test
    public void testDeleteCommunication() throws Exception {
        mockMvc.perform(delete("/api/communications/1"))
                .andExpect(status().isOk());

        Mockito.verify(communicationRepository).deleteById(1);  // Vérifie que la méthode a été appelée
    }
}*/