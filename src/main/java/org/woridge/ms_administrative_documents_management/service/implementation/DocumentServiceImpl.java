package org.woridge.ms_administrative_documents_management.service.implementation;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.woridge.ms_administrative_documents_management.mapper.DocumentMapper;
import org.woridge.ms_administrative_documents_management.model.Document;
import org.woridge.ms_administrative_documents_management.model.DocumentRequest;
import org.woridge.ms_administrative_documents_management.repository.DocumentRepository;
import org.woridge.ms_administrative_documents_management.service.DocumentService;

@Service
@Transactional
@AllArgsConstructor
public class DocumentServiceImpl implements DocumentService {

    private final DocumentRepository documentRepository;

    @Override
    public void addDocument(DocumentRequest documentRequest, String documentPath) {

        Document document = DocumentMapper.toDocument(documentRequest, documentPath);
        documentRepository.save(document);
    }
}
