package org.woridge.ms_administrative_documents_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.woridge.ms_administrative_documents_management.enumeration.RequestStatus;
import org.woridge.ms_administrative_documents_management.model.DocumentRequest;

import java.util.UUID;
import java.util.List;

@Repository
public interface DocumentRequestRepository extends JpaRepository<DocumentRequest, UUID> {

    List<DocumentRequest> findByUser_IdOrderByRequestDateDesc(UUID userId);

    List<DocumentRequest> findAllByOrderByRequestDateDesc();

    List<DocumentRequest> findByStatusOrderByRequestDateDesc(RequestStatus status);
    @Query("""
    SELECT dr FROM DocumentRequest dr
    JOIN FETCH dr.type dt
    LEFT JOIN FETCH dr.document d
    WHERE dr.user.cin = :cin
    ORDER BY dr.requestDate DESC
""")
    List<DocumentRequest> findAllByUserCinWithDetails(@Param("cin") String cin);
}