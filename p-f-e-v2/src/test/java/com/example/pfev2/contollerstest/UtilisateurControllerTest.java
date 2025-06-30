package com.example.pfev2.contollerstest;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import java.util.Arrays;
import com.example.pfev2.controllers.UtilisateurController;
import com.example.pfev2.entites.Utilisateur;
import com.example.pfev2.repositorys.UtilisateurRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
@WebMvcTest(UtilisateurController.class)
public class UtilisateurControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UtilisateurRepository utilisateurRepository;
    @Autowired
    private ObjectMapper objectMapper;
    private Utilisateur utilisateur1;
    @BeforeEach
    void setup() {
        //bff back for front ??
        //themfrorest
        //modernise

        utilisateur1 = new Utilisateur();
        utilisateur1.setId(1L);
        utilisateur1.setEmail("x@gmail.com");
        // initialise d'autres champs si besoin
    }

    @Test
    void testGetAllUtilisateurs() throws Exception {
        when(utilisateurRepository.findAll()).thenReturn(Arrays.asList(utilisateur1));

        mockMvc.perform(get("/api/utilisateurs"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(utilisateur1.getId()))
                .andExpect(jsonPath("$[0].email").value(utilisateur1.getEmail()));
    }

    @Test
    void testGetUtilisateurById() throws Exception {
        when(utilisateurRepository.getById(1L)).thenReturn(utilisateur1);

        mockMvc.perform(get("/api/utilisateurs/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(utilisateur1.getId()))
                .andExpect(jsonPath("$.email").value(utilisateur1.getEmail()));
    }

    @Test
    void testCreateUtilisateur() throws Exception {
        Utilisateur newUtilisateur = new Utilisateur();
        newUtilisateur.setEmail("x@gmail.com");

        when(utilisateurRepository.save(any(Utilisateur.class))).thenReturn(newUtilisateur);

        mockMvc.perform(post("/api/utilisateurs")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newUtilisateur)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("x@gmail.com"));
    }

    @Test
    void testUpdateUtilisateur() throws Exception {
        Utilisateur updatedUtilisateur = new Utilisateur();
        updatedUtilisateur.setEmail("x@gmail.com");

        when(utilisateurRepository.save(any(Utilisateur.class))).thenReturn(updatedUtilisateur);

        mockMvc.perform(put("/api/utilisateurs/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedUtilisateur)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("x@gmail.com"));
    }

    @Test
    void testDeleteUtilisateur() throws Exception {
        doNothing().when(utilisateurRepository).deleteById(1L);

        mockMvc.perform(delete("/api/utilisateurs/1"))
                .andExpect(status().isOk());
    }
}
