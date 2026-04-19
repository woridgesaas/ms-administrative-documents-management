package org.woridge.ms_administrative_documents_management.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.woridge.ms_administrative_documents_management.model.User;
import org.woridge.ms_administrative_documents_management.enumeration.RequestStatus;

import java.time.LocalDateTime;
import java.util.UUID;
import lombok.*;
@Entity
@Table(name = "document_requests")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DocumentRequest {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private LocalDateTime requestDate;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private RequestStatus status;

    private LocalDateTime processingDate;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(optional = false)
    @JoinColumn(name = "type_id", nullable = false)
    private DocumentType type;

    @OneToOne(mappedBy = "request", fetch = FetchType.LAZY)
    private Document document;
}