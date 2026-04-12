package org.woridge.ms_administrative_documents_management.service.implementation;

import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.woridge.ms_administrative_documents_management.messaging.publisher.DocumentRequestEventPublisher;
import org.woridge.ms_administrative_documents_management.model.DocumentType;
import org.woridge.ms_administrative_documents_management.dto.CreateDocumentRequest;
import org.woridge.ms_administrative_documents_management.dto.DocumentRequestResponse;
import org.woridge.ms_administrative_documents_management.exception.document.DocumentTypeNotFoundException;
import org.woridge.ms_administrative_documents_management.exception.user.UserNotFoundException;
import org.woridge.ms_administrative_documents_management.mapper.DocumentRequestMapper;
import org.woridge.ms_administrative_documents_management.model.DocumentRequest;
import org.woridge.ms_administrative_documents_management.model.User;
import org.woridge.ms_administrative_documents_management.repository.DocumentRequestRepository;
import org.woridge.ms_administrative_documents_management.repository.DocumentTypeRepository;
import org.woridge.ms_administrative_documents_management.repository.UserRepository;
import org.woridge.ms_administrative_documents_management.service.DocumentRequestService;

import java.util.List;
@Service
@RequiredArgsConstructor
@Transactional
public class DocumentRequestServiceImpl implements DocumentRequestService {

    private final DocumentRequestRepository requestRepository;
    private final UserRepository userRepository;
    private final DocumentTypeRepository typeRepository;
    private final DocumentRequestMapper mapper;
    private final DocumentRequestEventPublisher publisher;

    @Override
    public DocumentRequestResponse createDocumentRequest(CreateDocumentRequest dto, String cin) {

        // 1. Get user
        User user = userRepository.findByCin(cin)
                .orElseThrow(() -> new UserNotFoundException("User not found: " + cin));

        // 2. Get document type
        DocumentType type = typeRepository.findByCategory(dto.documentCode())
                .orElseThrow(() -> new DocumentTypeNotFoundException("Type not found"));

        // 3. Create entity
        DocumentRequest request = mapper.toEntity(user, type);

        // 4. Save
        DocumentRequest saved = requestRepository.save(request);

        // 5. Publish event
        publisher.publish(mapper.toEvent(saved));

        // 6. Return response
        return mapper.toResponse(saved);
    }

    @Override
    public List<DocumentRequestResponse> getEmployeeDocumentRequests(String cin) {

        User user = userRepository.findByCin(cin)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        return requestRepository.findByUser_IdOrderByRequestDateDesc(user.getId())
                .stream()
                .map(mapper::toResponse)
                .toList();
    }
}