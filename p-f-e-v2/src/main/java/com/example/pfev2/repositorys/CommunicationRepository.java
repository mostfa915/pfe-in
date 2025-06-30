package com.example.pfev2.repositorys;

import com.example.pfev2.entites.Communication;
import com.example.pfev2.entites.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface CommunicationRepository extends JpaRepository<Communication, Long> {

    List<Communication> findByReceiverId(Long receiverId);

    List<Communication> findBySenderId(Utilisateur senderId);

    @Query("SELECT c FROM Communication c WHERE c.receiver.id = :userId AND c.isRead = false")
    List<Communication> findUnreadByUserId(@Param("userId") Long userId);

    long countByReceiverIdAndIsReadFalse(Long receiverId);
    List<Communication> findByReceiverIdAndIsReadFalse(Long receiverId);
}