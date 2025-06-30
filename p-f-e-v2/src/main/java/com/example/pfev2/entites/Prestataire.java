package com.example.pfev2.entites;

// model/Prestataire.java




import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
public class Prestataire extends Utilisateur {
    private String specialite;



}