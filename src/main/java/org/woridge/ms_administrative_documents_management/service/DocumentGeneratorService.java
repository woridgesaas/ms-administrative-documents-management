package org.woridge.ms_administrative_documents_management.service;

import org.woridge.ms_administrative_documents_management.model.DocumentRequest;

public interface DocumentGeneratorService {

    void generateAndStoreDocument(DocumentRequest documentRequest, String tenantName);
}
