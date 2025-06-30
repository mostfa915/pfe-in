package com.example.pfev2.repositorys;

import com.example.pfev2.entites.Paiement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaiementRepository extends JpaRepository<Paiement, Long> {
}
