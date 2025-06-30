package com.example.pfev2.controllers;


import com.example.pfev2.entites.Document;
import com.example.pfev2.repositorys.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/documents")
@CrossOrigin(origins = "http://localhost:36713/")

public class DocumentController {

    @Autowired
    private DocumentRepository documentRepository;

    @GetMapping
    public List<Document> getAllDocuments() {
        return documentRepository.findAll();
    }
/*
    @GetMapping("/{id}")
    public Document getDocumentById(@PathVariable Long id) {
        return documentRepository.findById(id).orElseThrow();
    }*/
    @GetMapping("/{id}")
    public Document getDocumentById(@PathVariable Long id) {
        return documentRepository.findById(id)
                .map(document -> {
                    // Ici, vous pouvez inclure l'URL du fichier, par exemple :
                    document.setCheminFichier("http://your-server-url/files/" + document.getNomFichier());
                    return document;
                })
                .orElseThrow(() -> new RuntimeException("Document not found with id " + id));
    }

    @PostMapping("/upload")
    public ResponseEntity<?> uploadDocument(@RequestParam("file") MultipartFile file,
                                            @RequestParam("titre") String titre,
                                            @RequestParam("type") String type) throws IOException {
        // 1. Sauvegarde du fichier dans un dossier
        String uploadDir = "uploads/";
        File uploadFolder = new File(uploadDir);
        if (!uploadFolder.exists()) {
            uploadFolder.mkdirs();
        }

        String fileName = file.getOriginalFilename();
        Path filePath = Paths.get(uploadDir, fileName);
        Files.write(filePath, file.getBytes());

        // 2. Enregistrement des infos du document sans le contenu
        Document doc = new Document();
        doc.setTitre(titre);
        doc.setType(Document.TypeDocument.valueOf(type));
        doc.setNomFichier(fileName);
        doc.setCheminFichier(filePath.toString());

        Document saved = documentRepository.save(doc);
        return ResponseEntity.ok(saved);
    }


    @PutMapping("/{id}")
    public Document updateDocument(@PathVariable Long id, @RequestBody Document document) {
        document.setId(id);
        return documentRepository.save(document);
    }

    @DeleteMapping("/{id}")
    public void deleteDocument(@PathVariable Long id) {
        documentRepository.deleteById(id);
    }
}