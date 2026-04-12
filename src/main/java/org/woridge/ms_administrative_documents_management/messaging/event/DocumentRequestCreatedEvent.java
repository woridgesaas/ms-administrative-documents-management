package org.woridge.ms_administrative_documents_management.messaging.event;

import java.time.LocalDateTime;
import java.util.UUID;

public record DocumentRequestCreatedEvent(
        UUID requestId,
        String employeeCin,
        String employeeFullName,
        String documentCode,
        String documentGroup,
        LocalDateTime requestedAt
) {}