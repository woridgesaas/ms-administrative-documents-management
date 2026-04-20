package org.woridge.ms_administrative_documents_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.woridge.ms_administrative_documents_management.model.Document;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface DocumentRepository extends JpaRepository<Document, UUID> {

    @Query("""
                SELECT d FROM Document d
                JOIN FETCH d.request dr
                WHERE dr.id = :requestId
                AND dr.user.cin = :cin
            """)
    Optional<Document> findByRequestIdAndUserCin(
            @Param("requestId") UUID requestId,
            @Param("cin") String cin
    );
}