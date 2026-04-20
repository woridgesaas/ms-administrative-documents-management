package org.woridge.ms_administrative_documents_management.service;

import org.woridge.ms_administrative_documents_management.model.DocumentRequest;

import java.util.List;

import org.woridge.ms_administrative_documents_management.dto.GetDocumentRequest;

public interface DocumentService {

    void addDocument(DocumentRequest documentRequest, String documentPath);

    List<GetDocumentRequest> getEmployeeDocuments(String cin);

    // Resource downloadDocument(UUID documentRequestId, String cin);
}