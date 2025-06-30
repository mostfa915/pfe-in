package com.example.pfev2.controllers;

import com.example.pfev2.entites.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/*
@RestController
@RequestMapping("/api/test")
public class TestController {
@Autowired
    private  UtilisateurService utilisateurService;

    @GetMapping("/inserer")
    public String testInsertion() {
        utilisateurService.insererUtilisateur("123456", "exemple@mail.com", "hashedpassword", Utilisateur.Role.COPROPRIETAIRE);
        return "Utilisateur inséré avec succès";
    }
}
*/