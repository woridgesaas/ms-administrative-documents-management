package org.woridge.ms_administrative_documents_management.service;

import org.woridge.ms_administrative_documents_management.enumeration.DocumentTemplateType;

import java.util.Map;

public interface TemplateEngineService {
    String render(DocumentTemplateType templateName , Map<String, Object> data);
}
