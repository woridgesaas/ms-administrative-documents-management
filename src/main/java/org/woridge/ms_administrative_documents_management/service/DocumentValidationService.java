package org.woridge.ms_administrative_documents_management.service;

import org.woridge.ms_administrative_documents_management.dto.DocumentRequestResponse;
import org.woridge.ms_administrative_documents_management.dto.DocumentValidationResponse;
import org.woridge.ms_administrative_documents_management.enumeration.RequestStatus;   // ← manquait !
import org.woridge.ms_administrative_documents_management.enumeration.ValidationAction;

import java.util.List;
import java.util.UUID;

public interface DocumentValidationService {

    DocumentValidationResponse validate(UUID requestId, ValidationAction action, String rhCin);

    List<DocumentRequestResponse> getAllRequests();

    List<DocumentRequestResponse> getRequestsByStatus(RequestStatus status);  //
}