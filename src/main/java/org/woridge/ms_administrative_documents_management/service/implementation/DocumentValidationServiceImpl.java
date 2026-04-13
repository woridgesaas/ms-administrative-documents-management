
package org.woridge.ms_administrative_documents_management.service.implementation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
import org.woridge.ms_administrative_documents_management.service.DocumentValidationService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class DocumentValidationServiceImpl implements DocumentValidationService {

    private final DocumentRequestRepository requestRepository;
    private final DocumentRequestMapper mapper;
    private final DocumentValidatedEventPublisher validatedEventPublisher;

    @Override
    public DocumentValidationResponse validate(UUID requestId, ValidationAction action, String rhCin) {

        // 1. Récupérer la demande
        DocumentRequest request = requestRepository.findById(requestId)
                .orElseThrow(() -> new DocumentRequestNotFoundException(
                        "Demande introuvable avec l'id : " + requestId));

        // 2. Vérifier que la demande est bien PENDING
        if (request.getStatus() != RequestStatus.PENDING) {
            throw new IllegalStateException(
                    "Cette demande a déjà été traitée. Statut actuel : " + request.getStatus());
        }

        // 3. Appliquer l'action du RH
        RequestStatus newStatus = action == ValidationAction.APPROVE
                ? RequestStatus.APPROVED
                : RequestStatus.REJECTED;

        // 4. Mettre à jour la demande
        request.setStatus(newStatus);
        request.setProcessingDate(LocalDateTime.now());
        requestRepository.save(request);

        // 5. Publier l'événement RabbitMQ → ms-notifications
        DocumentValidatedEvent event = new DocumentValidatedEvent(
                request.getId(),
                request.getUser().getCin(),
                request.getType().getCategory(),
                newStatus.name(),
                request.getProcessingDate()
        );
        validatedEventPublisher.publish(event);

        log.info("Demande {} {} par RH {}",
                requestId, newStatus.name(), rhCin);

        // 6. Retourner la réponse
        return new DocumentValidationResponse(
                request.getId(),
                request.getUser().getCin(),
                request.getType().getCategory(),
                newStatus.name(),
                request.getProcessingDate()
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