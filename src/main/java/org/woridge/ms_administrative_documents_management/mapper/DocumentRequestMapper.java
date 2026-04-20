package org.woridge.ms_administrative_documents_management.mapper;

import org.springframework.stereotype.Component;
import org.woridge.ms_administrative_documents_management.dto.CreateDocumentRequest;
import org.woridge.ms_administrative_documents_management.dto.DocumentRequestResponse;
import org.woridge.ms_administrative_documents_management.enumeration.RequestStatus;
import org.woridge.ms_administrative_documents_management.messaging.event.DocumentRequestCreatedEvent;
import org.woridge.ms_administrative_documents_management.model.DocumentRequest;
import org.woridge.ms_administrative_documents_management.model.DocumentType;
import org.woridge.ms_administrative_documents_management.model.User;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class DocumentRequestMapper {

    public DocumentRequest toEntity(User user, DocumentType type) {
        return DocumentRequest.builder()
                .requestDate(LocalDateTime.now())
                .status(RequestStatus.PENDING)
                .user(user)
                .type(type)
                .build();
    }

    public DocumentRequestResponse toResponse(DocumentRequest request) {
        return new DocumentRequestResponse(
                request.getId(),
                request.getStatus().name(),
                request.getRequestDate(),
                request.getType().getCategory(),
                request.getType().getGroupName()
        );
    }

    public DocumentRequestCreatedEvent toEvent(DocumentRequest request) {
        return new DocumentRequestCreatedEvent(
                request.getId(),
                request.getUser().getCin(),
                request.getUser().getCin(),
                request.getType().getCategory(),
                request.getType().getGroupName(),
                request.getRequestDate()
        );
    }
}
