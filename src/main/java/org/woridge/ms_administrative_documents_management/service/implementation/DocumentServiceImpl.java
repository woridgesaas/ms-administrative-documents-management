package org.woridge.ms_administrative_documents_management.service.implementation;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.woridge.ms_administrative_documents_management.dto.GetDocumentRequest;
import org.woridge.ms_administrative_documents_management.mapper.DocumentMapper;
import org.woridge.ms_administrative_documents_management.mapper.DocumentRequestMapper;
import org.woridge.ms_administrative_documents_management.model.Document;
import org.woridge.ms_administrative_documents_management.model.DocumentRequest;
import org.woridge.ms_administrative_documents_management.repository.DocumentRepository;
import org.woridge.ms_administrative_documents_management.repository.DocumentRequestRepository;
import org.woridge.ms_administrative_documents_management.service.DocumentService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class DocumentServiceImpl implements DocumentService {

    private final DocumentRequestRepository documentRequestRepository;
    private final DocumentRepository documentRepository;
    private final DocumentRequestMapper documentRequestMapper;
    //private final MinioService minioService;

    @Override
    public void addDocument(DocumentRequest documentRequest, String documentPath) {

        Document document = DocumentMapper.toDocument(documentRequest, documentPath);
        documentRepository.save(document);
    }

    @Override
    public List<GetDocumentRequest> getEmployeeDocuments(String cin) {
        List<DocumentRequest> requests =
                documentRequestRepository.findAllByUserCinWithDetails(cin);

        return requests.stream()
                .map(documentRequestMapper::toDTO)
                .collect(Collectors.toList());
    }

   /* @Override
    public Resource downloadDocument(UUID documentRequestId, String cin) {
        Document document = documentRepository
                .findByRequestIdAndUserCin(documentRequestId, cin)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Document not found for request: " + documentRequestId
                ));

        if (document.getPath() == null || document.getPath().isBlank()) {
            throw new IllegalStateException("Document file is not available yet.");
        }
        return minioService.downloadFile(document.getPath());
    }*/
}