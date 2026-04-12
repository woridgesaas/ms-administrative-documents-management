package org.woridge.ms_administrative_documents_management.service;

import org.woridge.ms_administrative_documents_management.dto.DocumentTypeResponse;

import java.util.List;

public interface DocumentTypeService {

    List<DocumentTypeResponse> getAllTypes();
}