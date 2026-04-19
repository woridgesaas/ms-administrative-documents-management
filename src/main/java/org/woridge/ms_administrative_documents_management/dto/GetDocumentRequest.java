package org.woridge.ms_administrative_documents_management.dto;

import lombok.*;
import org.woridge.ms_administrative_documents_management.enumeration.RequestStatus;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetDocumentRequest {

    private UUID id;
    private String documentName;
    private String category;
    private LocalDateTime requestDate;
    private RequestStatus status;             // APPROVED, PENDING, REJETECTED
    private LocalDateTime processingDate;
    private boolean downloadable;      // true si status = APPROVED et document.path != null
}
