package org.woridge.ms_administrative_documents_management.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;
@Entity
@Table(name = "document_types")
@Getter
@Setter
public class DocumentType {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false, unique = true)
    private String category;

    @Column(name = "group_name",nullable = false)
    private String groupName;

    private String description;
}