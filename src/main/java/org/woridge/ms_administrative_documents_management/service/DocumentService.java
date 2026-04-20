package org.woridge.ms_administrative_documents_management.service;

import org.woridge.ms_administrative_documents_management.model.DocumentRequest;

public interface DocumentService {

    void addDocument(DocumentRequest documentRequest, String documentPath);
}
