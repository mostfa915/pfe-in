package com.example.pfev2.contollerstest;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import com.example.pfev2.controllers.DocumentController;
import com.example.pfev2.entites.Document;
import com.example.pfev2.repositorys.DocumentRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@WebMvcTest(DocumentController.class)
public class DocumentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DocumentRepository documentRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private Document doc1;
    private Document doc2;

    @BeforeEach
    void setup() {
        doc1 = new Document();
        doc1.setId(1L);
        doc1.setTitre("Doc 1");
        doc1.setType(Document.TypeDocument.AUTRE);
        doc1.setNomFichier("file1.pdf");
        doc1.setCheminFichier("uploads/file1.pdf");

        doc2 = new Document();
        doc2.setId(2L);
        doc2.setTitre("Doc 2");
        doc2.setType(Document.TypeDocument.COMPTE_RENDU);
        doc2.setNomFichier("file2.docx");
        doc2.setCheminFichier("uploads/file2.docx");
    }

    @Test
    void testGetAllDocuments() throws Exception {
        List<Document> docs = Arrays.asList(doc1, doc2);
        when(documentRepository.findAll()).thenReturn(docs);

        mockMvc.perform(get("/api/documents"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].titre").value("Doc 1"));
    }

    @Test
    void testGetDocumentById() throws Exception {
        when(documentRepository.findById(1L)).thenReturn(Optional.of(doc1));

        mockMvc.perform(get("/api/documents/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.titre").value("Doc 1"))
                .andExpect(jsonPath("$.cheminFichier").value("http://your-server-url/files/" + doc1.getNomFichier()));
    }
    @Test
    void testUploadDocument() throws Exception {
        // Create test uploads directory (or mock this in real implementation)
        new File("uploads").mkdirs();

        MockMultipartFile file = new MockMultipartFile(
                "file",
                "testfile.pdf",
                MediaType.APPLICATION_PDF_VALUE,
                "Contenu du fichier test".getBytes()
        );

        String titre = "Titre Test";
        String type = "AUTRE"; // Must match exact enum name

        Document savedDoc = new Document();
        savedDoc.setId(1L);
        savedDoc.setTitre(titre);
        savedDoc.setType(Document.TypeDocument.AUTRE);
        savedDoc.setNomFichier("testfile.pdf");
        savedDoc.setCheminFichier("uploads/testfile.pdf");

        when(documentRepository.save(any(Document.class))).thenReturn(savedDoc);

        mockMvc.perform(multipart("/api/documents/upload")
                        .file(file)
                        .param("titre", titre)
                        .param("type", type)
                        .contentType(MediaType.MULTIPART_FORM_DATA))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.titre").value(titre))
                .andExpect(jsonPath("$.type").value("AUTRE"))
                .andExpect(jsonPath("$.nomFichier").value("testfile.pdf"))
                .andExpect(jsonPath("$.cheminFichier").value("uploads/testfile.pdf"));

        // Verify file was actually written (optional)
        assertTrue(Files.exists(Paths.get("uploads/testfile.pdf")));

        // Cleanup after test
        Files.deleteIfExists(Paths.get("uploads/testfile.pdf"));
    }
    @Test
    void testUpdateDocument() throws Exception {
        Document updatedDoc = new Document();
        updatedDoc.setId(1L);
        updatedDoc.setTitre("Titre Modifié");
        updatedDoc.setType(Document.TypeDocument.AUTRE);
        updatedDoc.setNomFichier("file1.pdf");
        updatedDoc.setCheminFichier("uploads/file1.pdf");

        when(documentRepository.save(any(Document.class))).thenReturn(updatedDoc);

        mockMvc.perform(put("/api/documents/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedDoc)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.titre").value("Titre Modifié"));
    }

    @Test
    void testDeleteDocument() throws Exception {
        doNothing().when(documentRepository).deleteById(1L);

        mockMvc.perform(delete("/api/documents/1"))
                .andExpect(status().isOk());
    }
}
