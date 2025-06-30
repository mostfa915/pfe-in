package com.example.pfev2.repositorys;

import com.example.pfev2.entites.Document;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentRepository extends JpaRepository<Document, Long > {
}
