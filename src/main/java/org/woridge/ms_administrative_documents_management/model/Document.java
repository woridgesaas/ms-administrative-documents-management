package org.woridge.ms_administrative_documents_management.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "documents")
@Getter
@Setter
@NoArgsConstructor
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @Column(name = "generation_date")
    private LocalDateTime generationDate;

    @Column(name = "path")
    private String path;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "request_id")
    private DocumentRequest request;
}
