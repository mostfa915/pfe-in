package com.example.pfev2.contollerstest;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.Optional;

import com.example.pfev2.controllers.SyndicController;
import com.example.pfev2.entites.Syndic;
import com.example.pfev2.repositorys.SyndicRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(SyndicController.class)
public class SyndicControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SyndicRepository syndicRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private Syndic syndic1;

    @BeforeEach
    void setup() {
        syndic1 = new Syndic();
        syndic1.setId(1L);
        syndic1.setEmail("Syndic 1");
        // initialise d'autres champs si besoin
    }

    @Test
    void testGetAllSyndic() throws Exception {
        when(syndicRepository.findAll()).thenReturn(Arrays.asList(syndic1));

        mockMvc.perform(get("/api/Syndic"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(syndic1.getId()))
                .andExpect(jsonPath("$[0].email").value(syndic1.getEmail()));
    }

    @Test
    void testGetSyndicById() throws Exception {
        when(syndicRepository.getById(1)).thenReturn(syndic1);

        mockMvc.perform(get("/api/Syndic/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(syndic1.getId()))
                .andExpect(jsonPath("$.email").value(syndic1.getEmail()));
    }

    @Test
    void testCreateSyndic() throws Exception {
        Syndic newSyndic = new Syndic();
        newSyndic.setEmail("x@gmail.com");

        when(syndicRepository.save(any(Syndic.class))).thenReturn(newSyndic);

        mockMvc.perform(post("/api/Syndic")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newSyndic)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("x@gmail.com"));
    }

    @Test
    void testUpdateSyndic() throws Exception {
        Syndic updatedSyndic = new Syndic();
        updatedSyndic.setEmail("x@gmail.com");

        when(syndicRepository.save(any(Syndic.class))).thenReturn(updatedSyndic);

        mockMvc.perform(put("/api/Syndic/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedSyndic)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("x@gmail.com"));
    }

    @Test
    void testDeleteSyndic() throws Exception {
        doNothing().when(syndicRepository).deleteById(1);

        mockMvc.perform(delete("/api/Syndic/1"))
                .andExpect(status().isOk());
    }
}
