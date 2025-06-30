package com.example.pfev2.entites;
// model/Paiement.java
import jakarta.persistence.*;
import lombok.Data;
@Entity
@Data
public class Paiement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Float montant;
    @Enumerated(EnumType.STRING)
    private MethodePaiement methode;
    @ManyToOne
    private Coproprietaire payeur;
    public enum MethodePaiement {
        CARTE, VIREMENT, CHEQUE, ESPECES
    }
}