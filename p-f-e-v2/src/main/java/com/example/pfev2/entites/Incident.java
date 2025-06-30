package com.example.pfev2.entites;

// model/Incident.java




import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Incident {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    @Enumerated(EnumType.STRING)
    private Priorite priorite;

    @Enumerated(EnumType.STRING)
    private Statut statut;

    private String survChat;

    @ManyToOne
    private Utilisateur declarant;

    public enum Priorite {
        BASSE, MOYENNE, HAUTE
    }

    public enum Statut {
        NOUVEAU, EN_COURS, RESOLU
    }
}