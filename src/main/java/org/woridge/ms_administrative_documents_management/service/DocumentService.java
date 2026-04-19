package org.woridge.ms_administrative_documents_management.service;

import java.util.List;

import org.woridge.ms_administrative_documents_management.dto.GetDocumentRequest;

public interface DocumentService {

    List<GetDocumentRequest> getEmployeeDocuments(String cin);
   // Resource downloadDocument(UUID documentRequestId, String cin);
}