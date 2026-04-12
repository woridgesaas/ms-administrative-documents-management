package org.woridge.ms_administrative_documents_management.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record DocumentRequestResponse(
        UUID id,
        String status,
        LocalDateTime requestDate,
        String documentCode,
        String documentGroup
) {}