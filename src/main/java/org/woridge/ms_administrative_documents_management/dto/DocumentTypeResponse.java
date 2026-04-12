package org.woridge.ms_administrative_documents_management.dto;


public record DocumentTypeResponse(
        String category,
        String group,
        String description
) {}