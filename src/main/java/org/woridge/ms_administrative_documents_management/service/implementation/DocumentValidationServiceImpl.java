package org.woridge.ms_administrative_documents_management.service.implementation;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.woridge.ms_administrative_documents_management.dto.DocumentRequestResponse;
import org.woridge.ms_administrative_documents_management.dto.DocumentValidationResponse;
import org.woridge.ms_administrative_documents_management.enumeration.RequestStatus;
import org.woridge.ms_administrative_documents_management.enumeration.ValidationAction;
import org.woridge.ms_administrative_documents_management.exception.document.DocumentRequestNotFoundException;
import org.woridge.ms_administrative_documents_management.mapper.DocumentRequestMapper;
import org.woridge.ms_administrative_documents_management.messaging.event.DocumentValidatedEvent;
import org.woridge.ms_administrative_documents_management.messaging.publisher.DocumentValidatedEventPublisher;
import org.woridge.ms_administrative_documents_management.model.DocumentRequest;
import org.woridge.ms_administrative_documents_management.repository.DocumentRequestRepository;
import org.woridge.ms_administrative_documents_management.service.DocumentGeneratorService;
import org.woridge.ms_administrative_documents_management.service.DocumentValidationService;
import org.woridge.ms_administrative_documents_management.utils.RequestUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
@Transactional
public class DocumentValidationServiceImpl implements DocumentValidationService {

    private final DocumentRequestRepository requestRepository;
    private final DocumentRequestMapper mapper;
    private final DocumentValidatedEventPublisher validatedEventPublisher;
    private final DocumentGeneratorService documentGeneratorService;
    private final HttpServletRequest httpServletRequest;

    @Override
    public DocumentValidationResponse validate(UUID requestId, ValidationAction action, String rhCin) {

        // Find the document request
        DocumentRequest documentRequest = requestRepository.findById(requestId).orElseThrow(() ->
                new DocumentRequestNotFoundException("Demande introuvable avec l'id : " + requestId));

        // 2. Vérifier que la demande est bien PENDING
        if (documentRequest.getStatus() != RequestStatus.PENDING) {
            throw new IllegalStateException(
                    "Cette demande a déjà été traitée. Statut actuel : " + documentRequest.getStatus());
        }

        // 3. Appliquer l'action du RH
        RequestStatus newStatus = action == ValidationAction.APPROVE
                ? RequestStatus.APPROVED
                : RequestStatus.REJECTED;

        // 4. Mettre à jour la demande
        documentRequest.setStatus(newStatus);
        documentRequest.setProcessingDate(LocalDateTime.now());
        requestRepository.save(documentRequest);

        // Generate and save approved request document
        if (newStatus == RequestStatus.APPROVED) {

            String tenantName = RequestUtils.extractSubdomainFromHeaderRequest(httpServletRequest);
            documentGeneratorService.generateAndStoreDocument(documentRequest, tenantName);
        }

        // 5. Publier l'événement RabbitMQ → ms-notifications
        DocumentValidatedEvent event = new DocumentValidatedEvent(
                documentRequest.getId(),
                documentRequest.getUser().getCin(),
                documentRequest.getType().getCategory(),
                newStatus.name(),
                documentRequest.getProcessingDate()
        );
        validatedEventPublisher.publish(event);

        // 6. Retourner la réponse
        return new DocumentValidationResponse(
                documentRequest.getId(),
                documentRequest.getUser().getCin(),
                documentRequest.getType().getCategory(),
                newStatus.name(),
                documentRequest.getProcessingDate()
        );
    }

    @Override
    @Transactional(readOnly = true)
    public List<DocumentRequestResponse> getAllRequests() {
        return requestRepository.findAllByOrderByRequestDateDesc()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<DocumentRequestResponse> getRequestsByStatus(RequestStatus status) {
        return requestRepository.findByStatusOrderByRequestDateDesc(status)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }
}