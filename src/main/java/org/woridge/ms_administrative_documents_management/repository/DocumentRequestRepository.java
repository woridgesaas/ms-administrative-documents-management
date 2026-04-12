package org.woridge.ms_administrative_documents_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.woridge.ms_administrative_documents_management.model.DocumentRequest;

import java.util.UUID;
import java.util.List;

@Repository
public interface DocumentRequestRepository extends JpaRepository<DocumentRequest, UUID> {

    List<DocumentRequest> findByUser_IdOrderByRequestDateDesc(UUID userId);
}