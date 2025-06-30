package com.example.pfev2.controllers;
import com.example.pfev2.entites.Prestataire;
import com.example.pfev2.repositorys.PrestataireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/prestataires")
public class PrestataireController {
    @Autowired
    public PrestataireRepository prestataireRepository;
    public PrestataireController(PrestataireRepository prestataireRepository) {
        this.prestataireRepository = prestataireRepository;
    }
    @GetMapping
    public List<Prestataire> getAllPrestataires() {
        return prestataireRepository.findAll();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Prestataire> getPrestataireById(@PathVariable Long id) {
        return prestataireRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @PostMapping
    public Prestataire createPrestataire(@RequestBody Prestataire prestataire) {
        return prestataireRepository.save(prestataire);
    }
    @PutMapping("/{id}")
    public Prestataire updatePrestataire(@PathVariable Long id, @RequestBody Prestataire prestataire) {
        prestataire.setId(id);
        return prestataireRepository.save(prestataire);
    }
    @DeleteMapping("/{id}")
    public void deletePrestataire(@PathVariable Long id) {
        prestataireRepository.deleteById(id);
    }
}
