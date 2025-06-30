package com.example.pfev2.contollerstest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import com.example.pfev2.controllers.IncidentController;
import com.example.pfev2.entites.Incident;
import com.example.pfev2.repositorys.IncidentRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMultipartHttpServletRequestBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@WebMvcTest(IncidentController.class)
public class IncidentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IncidentRepository incidentRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private Incident incident1;
    private Incident incident2;

    @BeforeEach
    void setup() {
        incident1 = new Incident();
        incident1.setId(1L);
        incident1.setDescription("Incident 1");
        incident1.setPriorite(Incident.Priorite.valueOf("MOYENNE"));
        incident1.setStatut(Incident.Statut.valueOf("RESOLU"));
        incident1.setSurvChat("file1.txt");

        incident2 = new Incident();
        incident2.setId(2L);
        incident2.setDescription("Incident 2");
        incident2.setPriorite(Incident.Priorite.valueOf("MOYENNE"));
        incident2.setStatut(Incident.Statut.valueOf("RESOLU"));
        incident2.setSurvChat("file2.txt");
    }

    @Test
    void testGetAllIncidents() throws Exception {
        List<Incident> incidents = Arrays.asList(incident1, incident2);
        when(incidentRepository.findAll()).thenReturn(incidents);

        mockMvc.perform(get("/api/incidents"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].description").value("Incident 1"));
    }

    @Test
    void testGetIncidentById() throws Exception {
        when(incidentRepository.findById(1L)).thenReturn(Optional.of(incident1));

        mockMvc.perform(get("/api/incidents/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.description").value("Incident 1"));
    }

    @Test
    void testCreateIncident() throws Exception {
        // Créer un MockMultipartFile pour le fichier
        MockMultipartFile file = new MockMultipartFile("file", "test.txt", MediaType.TEXT_PLAIN_VALUE, "Contenu du fichier".getBytes());

        // JSON sérialisé pour l’incident
        MockMultipartFile incidentJson = new MockMultipartFile("incident", "", MediaType.APPLICATION_JSON_VALUE,
                objectMapper.writeValueAsBytes(incident1));

        when(incidentRepository.save(any(Incident.class))).thenReturn(incident1);

        mockMvc.perform(multipart("/api/incidents")
                        .file(incidentJson)
                        .file(file)
                        .contentType(MediaType.MULTIPART_FORM_DATA))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.description").value("Incident 1"));
    }

    @Test
    void testUpdateIncident_WithFile() throws Exception {
        when(incidentRepository.findById(1L)).thenReturn(Optional.of(incident1));
        when(incidentRepository.save(any(Incident.class))).thenReturn(incident1);

        Incident updatedIncident = new Incident();
        updatedIncident.setDescription("Description mise à jour");
        updatedIncident.setPriorite(Incident.Priorite.valueOf("MOYENNE"));
        updatedIncident.setStatut(Incident.Statut.valueOf("RESOLU"));

        MockMultipartFile file = new MockMultipartFile("file", "updated.txt", MediaType.TEXT_PLAIN_VALUE, "Nouveau contenu".getBytes());
        MockMultipartFile incidentJson = new MockMultipartFile("incident", "", MediaType.APPLICATION_JSON_VALUE,
                objectMapper.writeValueAsBytes(updatedIncident));

        MockMultipartHttpServletRequestBuilder builder = multipart("/api/incidents/1");
        builder.with(request -> {
            request.setMethod("PUT"); // Par défaut multipart() fait un POST, on force PUT
            return request;
        });

        mockMvc.perform(builder
                        .file(incidentJson)
                        .file(file)
                        .contentType(MediaType.MULTIPART_FORM_DATA))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.description").value("Description mise à jour"))
                .andExpect(jsonPath("$.priorite").value("MOYENNE"))
                .andExpect(jsonPath("$.statut").value("RESOLU"));
    }

    @Test
    void testUpdateIncident_WithoutFile() throws Exception {
        when(incidentRepository.findById(1L)).thenReturn(Optional.of(incident1));
        when(incidentRepository.save(any(Incident.class))).thenReturn(incident1);

        Incident updatedIncident = new Incident();
        updatedIncident.setDescription("Description mise à jour");
        updatedIncident.setPriorite(Incident.Priorite.valueOf("MOYENNE"));
        updatedIncident.setStatut(Incident.Statut.valueOf("RESOLU"));

        MockMultipartFile incidentJson = new MockMultipartFile("incident", "", MediaType.APPLICATION_JSON_VALUE,
                objectMapper.writeValueAsBytes(updatedIncident));

        MockMultipartHttpServletRequestBuilder builder = multipart("/api/incidents/1");
        builder.with(request -> {
            request.setMethod("PUT");
            return request;
        });

        mockMvc.perform(builder
                        .file(incidentJson)
                        .contentType(MediaType.MULTIPART_FORM_DATA))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.description").value("Description mise à jour"))
                .andExpect(jsonPath("$.priorite").value("MOYENNE"))
                .andExpect(jsonPath("$.statut").value("RESOLU"));
    }

    @Test
    void testDeleteIncident() throws Exception {
        doNothing().when(incidentRepository).deleteById(1L);

        mockMvc.perform(delete("/api/incidents/1"))
                .andExpect(status().isOk());
    }
}
