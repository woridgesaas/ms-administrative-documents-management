package org.woridge.ms_administrative_documents_management.mapper;

import org.woridge.ms_administrative_documents_management.model.Document;
import org.woridge.ms_administrative_documents_management.model.DocumentRequest;

public class DocumentMapper {

    public static Document toDocument(DocumentRequest documentRequest, String path) {

        return Document
                .builder()
                .documentRequest(documentRequest)
                .path(path)
                .build();
    }
}
