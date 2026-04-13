// src/main/java/.../messaging/event/DocumentValidatedEvent.java
package org.woridge.ms_administrative_documents_management.messaging.event;

import java.time.LocalDateTime;
import java.util.UUID;

public record DocumentValidatedEvent(

        UUID requestId,        // ID de la demande
        String employeeCin,    // CIN de l'employé à notifier
        String documentCode,   // type du document demandé
        String status,         // "APPROVED" ou "REJECTED"
        LocalDateTime processedAt  // date de traitement
) {}