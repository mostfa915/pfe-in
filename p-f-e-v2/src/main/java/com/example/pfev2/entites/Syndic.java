package com.example.pfev2.entites;

// model/Syndic.java



import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Entity
@DiscriminatorValue("SYNDIC")
@Data

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Syndic extends Utilisateur {

}