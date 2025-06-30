package com.example.pfev2.contollerstest;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import com.example.pfev2.controllers.CoproprietaireController;
import com.example.pfev2.entites.Coproprietaire;
import com.example.pfev2.repositorys.CoproprietaireRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

@WebMvcTest(CoproprietaireController.class)
public class CoproprietaireControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CoproprietaireRepository coproprietaireRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private Coproprietaire copro1;
    private Coproprietaire copro2;

    @BeforeEach
    void setup() {
        copro1 = new Coproprietaire();
        copro1.setId(1L);
        copro1.setEmail("Dupont");

        copro2 = new Coproprietaire();
        copro2.setId(2L);
        copro2.setEmail("Martin");
    }

    @Test
    void testGetAllCoproprietaires() throws Exception {
        List<Coproprietaire> coproList = Arrays.asList(copro1, copro2);
        when(coproprietaireRepository.findAll()).thenReturn(coproList);

        mockMvc.perform(get("/api/Coproprietaires"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].email").value("Dupont"));
    }

    @Test
    void testGetCoproprietaireById() throws Exception {
        when(coproprietaireRepository.getById(1)).thenReturn(copro1);

        mockMvc.perform(get("/api/Coproprietaires/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("Dupont"));
    }

    @Test
    void testCreateCoproprietaire() throws Exception {
        when(coproprietaireRepository.save(any(Coproprietaire.class))).thenReturn(copro1);

        mockMvc.perform(post("/api/Coproprietaires")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(copro1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("Dupont"));
    }

    @Test
    void testUpdateCoproprietaire() throws Exception {
        copro1.setEmail("Dupont Updated");
        when(coproprietaireRepository.save(any(Coproprietaire.class))).thenReturn(copro1);

        mockMvc.perform(put("/api/Coproprietaires/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(copro1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("Dupont Updated"));
    }

    @Test
    void testDeleteCoproprietaire() throws Exception {
        doNothing().when(coproprietaireRepository).deleteById(1);

        mockMvc.perform(delete("/api/Coproprietaires/1"))
                .andExpect(status().isOk());
    }
}
