package org.woridge.ms_administrative_documents_management.service;

import org.woridge.ms_administrative_documents_management.dto.CreateDocumentRequest;
import org.woridge.ms_administrative_documents_management.dto.DocumentRequestResponse;
import org.woridge.ms_administrative_documents_management.model.DocumentType;

import java.util.List;
import java.util.Optional;

public interface DocumentRequestService {

    DocumentRequestResponse createDocumentRequest(CreateDocumentRequest request, String cin);

    List<DocumentRequestResponse> getEmployeeDocumentRequests(String cin);
}