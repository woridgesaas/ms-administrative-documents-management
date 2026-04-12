package org.woridge.ms_administrative_documents_management.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateDocumentRequest(
        @NotBlank(message = "documentCode is required")
        String documentCode
) {}