package com.example.pfev2.controllers;

import com.example.pfev2.entites.Communication;
import com.example.pfev2.entites.Coproprietaire;
import com.example.pfev2.repositorys.CommunicationRepository;
import com.example.pfev2.repositorys.CoproprietaireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/Coproprietaires")
@CrossOrigin(origins = "http://localhost:36713/")

public class CoproprietaireController {

    @Autowired
    public CoproprietaireRepository coproprietaireRepository ;

    @GetMapping
    public List<Coproprietaire> getAllCoproprietaire() {
        return coproprietaireRepository.findAll();
    }

    @GetMapping("/{id}")
    public Coproprietaire getCoproprietaireById(@PathVariable Integer id) {
        return coproprietaireRepository.getById(id);
    }

    @PostMapping
    public Coproprietaire createCoproprietaire(@RequestBody Coproprietaire coproprietaire) {
        return coproprietaireRepository.save(coproprietaire);
    }

    @PutMapping("/{id}")
    public Coproprietaire updateCoproprietaire(@PathVariable Long id, @RequestBody Coproprietaire coproprietaire) {
        coproprietaire.setId(id);
        return coproprietaireRepository.save(coproprietaire);
    }

    @DeleteMapping("/{id}")
    public void deleteCoproprietaire(@PathVariable Integer id) {
        coproprietaireRepository.deleteById(id);
    }}