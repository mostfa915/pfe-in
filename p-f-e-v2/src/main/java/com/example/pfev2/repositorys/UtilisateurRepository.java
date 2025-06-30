package com.example.pfev2.repositorys;

import com.example.pfev2.entites.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
    Optional<Utilisateur> findByEmail(String email);
}
