package org.woridge.ms_administrative_documents_management.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "documents")
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String path;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private Instant generatedAt;

    @OneToOne(optional = false)
    @JoinColumn(name = "document_request_id", nullable = false, unique = true)
    private DocumentRequest documentRequest;
}
