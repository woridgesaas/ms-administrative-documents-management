// src/main/java/.../dto/DocumentValidationResponse.java
package org.woridge.ms_administrative_documents_management.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record DocumentValidationResponse(
        UUID requestId,
        String employeeCin,
        String documentCode,
        String newStatus,          // "APPROVED" ou "REJECTED"
        LocalDateTime processedAt
) {}