package org.woridge.ms_administrative_documents_management.mapper;

import org.springframework.stereotype.Component;
import org.woridge.ms_administrative_documents_management.dto.DocumentTypeResponse;
import org.woridge.ms_administrative_documents_management.model.DocumentType;

@Component
public class DocumentTypeMapper {

    public DocumentTypeResponse toDto(DocumentType entity) {
        return new DocumentTypeResponse(
                entity.getCategory(),     // code
                entity.getGroupName(),    // group UI
                entity.getDescription()
        );
    }
}