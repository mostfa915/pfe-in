package com.example.pfev2.controllers;

import com.example.pfev2.entites.Coproprietaire;
import com.example.pfev2.entites.Syndic;
import com.example.pfev2.repositorys.SyndicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/Syndic")
public class SyndicController {

    @Autowired
    public SyndicRepository SyndicRepository;

    @GetMapping
    public List<Syndic> getAllSyndic() {
        return SyndicRepository.findAll();
    }

    @GetMapping("/{id}")
    public Syndic getSyndicById(@PathVariable Integer id) {
        return SyndicRepository.getById(id);
    }
    @PostMapping
    public Syndic createSyndic(@RequestBody Syndic syndic) {
        return SyndicRepository.save(syndic);
    }
    @PutMapping("/{id}")
    public Syndic updateSyndic(@PathVariable Long id, @RequestBody Syndic syndic) {
        syndic.setId(id);
        return SyndicRepository.save(syndic);
    }
    @DeleteMapping("/{id}")
    public void deleteSyndic(@PathVariable Integer id) {
        SyndicRepository.deleteById(id);
    }
}
