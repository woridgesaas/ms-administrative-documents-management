package org.woridge.ms_administrative_documents_management.mapper;

import org.springframework.stereotype.Component;
import org.woridge.ms_administrative_documents_management.dto.CreateDocumentRequest;
import org.woridge.ms_administrative_documents_management.dto.DocumentRequestResponse;
import org.woridge.ms_administrative_documents_management.dto.GetDocumentRequest;
import org.woridge.ms_administrative_documents_management.enumeration.RequestStatus;
import org.woridge.ms_administrative_documents_management.messaging.event.DocumentRequestCreatedEvent;
import org.woridge.ms_administrative_documents_management.model.DocumentRequest;
import org.woridge.ms_administrative_documents_management.model.DocumentType;
import org.woridge.ms_administrative_documents_management.model.User;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class DocumentRequestMapper {

    public DocumentRequest toEntity(User user, DocumentType type) {
        return DocumentRequest.builder()
                .id(UUID.randomUUID())
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
    public GetDocumentRequest toDTO(DocumentRequest dr) {
        boolean downloadable = RequestStatus.APPROVED.equals(dr.getStatus())
                && dr.getDocument() != null
                && dr.getDocument().getPath() != null;

        return GetDocumentRequest.builder()
                .id(dr.getId())
                .documentName(formatCategoryLabel(dr.getType().getCategory()))
                .category(formatGroupLabel(dr.getType().getGroupName()))
                .requestDate(dr.getRequestDate())
                .status(dr.getStatus())
                .processingDate(dr.getProcessingDate())
                .downloadable(downloadable)
                .build();
    }

    private String formatCategoryLabel(String category) {
        if (category == null) return "";
        return Arrays.stream(category.split("_"))
                .map(w -> Character.toUpperCase(w.charAt(0)) + w.substring(1).toLowerCase())
                .collect(Collectors.joining(" "));
    }

    private String formatGroupLabel(String groupName) {
        if (groupName == null) return "";
        return switch (groupName) {
            case "ATTESTATIONS"    -> "Attestations";
            case "END_OF_CONTRACT" -> "Fin de contrat";
            case "DISCIPLINE"      -> "Discipline";
            case "TRAINING"        -> "Formation";
            case "INTERNAL_RH"     -> "Documents internes RH";
            default                -> groupName;
        };
    }
}
