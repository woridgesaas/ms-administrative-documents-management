package org.woridge.ms_administrative_documents_management.service.implementation;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.woridge.ms_administrative_documents_management.dto.DocumentTypeResponse;
import org.woridge.ms_administrative_documents_management.mapper.DocumentTypeMapper;
import org.woridge.ms_administrative_documents_management.model.DocumentType;
import org.woridge.ms_administrative_documents_management.repository.DocumentTypeRepository;
import org.woridge.ms_administrative_documents_management.service.DocumentTypeService;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DocumentTypeServiceImpl implements DocumentTypeService {

    private final DocumentTypeRepository documentTypeRepository;
    private final DocumentTypeMapper mapper;

    @Transactional
    public List<DocumentTypeResponse> getAllTypes() {

        return documentTypeRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(DocumentType::getGroupName))
                .map(mapper::toDto)
                .toList();
    }
}