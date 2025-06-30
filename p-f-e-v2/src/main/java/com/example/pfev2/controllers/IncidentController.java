package com.example.pfev2.controllers;
import com.example.pfev2.entites.Incident;
import com.example.pfev2.repositorys.IncidentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
@RestController
@RequestMapping("/api/incidents")
public class IncidentController {
    @Autowired
    private IncidentRepository incidentRepository;
    @GetMapping
    public List<Incident> getAllIncidents() {
        return incidentRepository.findAll();
    }
    @GetMapping("/{id}")
    public Incident getIncidentById(@PathVariable Long id) {
        return incidentRepository.findById(id).orElseThrow();
    }

  //  private static final String UPLOAD_DIR = "uploads/";

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Incident createIncident(
            @RequestPart("incident") Incident incident,
            @RequestPart("file") MultipartFile file) throws IOException {
        incident.setId(null);
        // Dossier de stockage
        String uploadDir = "uploads/";
        Files.createDirectories(Paths.get(uploadDir));

        // Nom du fichier
        String fileName = file.getOriginalFilename();

        // Stocker le fichier
        Path filePath = Paths.get(uploadDir, fileName);
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        // Sauvegarder le nom du fichier dans l’entité
        incident.setSurvChat("http://localhost:8081/uploads/"+fileName);

        return incidentRepository.save(incident);
    }
    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Incident> updateIncident(
            @PathVariable Long id,
            @RequestPart("incident") Incident updatedIncident,
            @RequestPart(value = "file", required = false) MultipartFile file) {

        return incidentRepository.findById(id).map(incident -> {
            incident.setDescription(updatedIncident.getDescription());
            incident.setPriorite(updatedIncident.getPriorite());
            incident.setStatut(updatedIncident.getStatut());

            if (file != null && !file.isEmpty()) {
                String fileName = file.getOriginalFilename();
                Path filePath = Paths.get("uploads", fileName);
                try {
                    Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
                    incident.setSurvChat(fileName);
                } catch (IOException e) {
                    throw new RuntimeException("Erreur lors de la sauvegarde du fichier", e);
                }
            }

            return ResponseEntity.ok(incidentRepository.save(incident));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public void deleteIncident(@PathVariable Long id) {
        incidentRepository.deleteById(id);
    }
}



