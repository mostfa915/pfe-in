package com.example.pfev2.contollerstest;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.Optional;

import com.example.pfev2.controllers.PrestataireController;
import com.example.pfev2.entites.Prestataire;
import com.example.pfev2.repositorys.PrestataireRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;

@WebMvcTest(PrestataireController.class)
public class PrestataireControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PrestataireRepository prestataireRepository;

    @Autowired
    private ObjectMapper objectMapper;  // pour JSON

    private Prestataire prestataire1;

    @BeforeEach
    void setup() {
        prestataire1 = new Prestataire();
        prestataire1.setId(1L);
        prestataire1.setEmail("Prestataire 1");
        // initialise les autres champs de prestataire si besoin
    }

    @Test
    void testGetAllPrestataires() throws Exception {
        when(prestataireRepository.findAll()).thenReturn(Arrays.asList(prestataire1));

        mockMvc.perform(get("/api/prestataires"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(prestataire1.getId()))
                .andExpect(jsonPath("$[0].email").value(prestataire1.getEmail()));
    }

    @Test
    void testGetPrestataireByIdFound() throws Exception {
        when(prestataireRepository.findById(1L)).thenReturn(Optional.of(prestataire1));

        mockMvc.perform(get("/api/prestataires/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(prestataire1.getId()))
                .andExpect(jsonPath("$.email").value(prestataire1.getEmail()));
    }

    @Test
    void testGetPrestataireByIdNotFound() throws Exception {
        when(prestataireRepository.findById(2L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/prestataires/2"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testCreatePrestataire() throws Exception {
        Prestataire newPrestataire = new Prestataire();
        newPrestataire.setEmail("Nouveau Prestataire");

        when(prestataireRepository.save(any(Prestataire.class))).thenReturn(newPrestataire);

        mockMvc.perform(post("/api/prestataires")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newPrestataire)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("Nouveau Prestataire"));
    }

    @Test
    void testUpdatePrestataire() throws Exception {
        Prestataire updatedPrestataire = new Prestataire();
        updatedPrestataire.setEmail("Prestataire Mis à Jour");

        when(prestataireRepository.save(any(Prestataire.class))).thenReturn(updatedPrestataire);

        mockMvc.perform(put("/api/prestataires/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedPrestataire)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("Prestataire Mis à Jour"));
    }

    @Test
    void testDeletePrestataire() throws Exception {
        doNothing().when(prestataireRepository).deleteById(1L);

        mockMvc.perform(delete("/api/prestataires/1"))
                .andExpect(status().isOk());
    }
}
