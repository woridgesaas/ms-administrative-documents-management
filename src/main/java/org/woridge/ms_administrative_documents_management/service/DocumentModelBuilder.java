package org.woridge.ms_administrative_documents_management.service;

import org.woridge.ms_administrative_documents_management.enumeration.DocumentTemplateType;

import java.util.Map;

public interface DocumentModelBuilder {

    Map<String, Object> buildDocumentModel(DocumentTemplateType documentTemplateType, String employeeCIN);
}
