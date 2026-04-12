package org.woridge.ms_administrative_documents_management.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;
@Entity
@Table(name = "document_types")
@Getter
@Setter
public class DocumentType {

    @Id
    private UUID id;

    @Column(nullable = false, unique = true)
    private String category;

    @Column(name = "group_name",nullable = false)
    private String groupName;

    private String description;
}