package com.example.pfev2.repositorys;

// repository/IncidentRepository.java


import org.springframework.data.jpa.repository.JpaRepository;

public interface IncidentRepository extends JpaRepository<com.example.pfev2.entites.Incident, Long> {
}