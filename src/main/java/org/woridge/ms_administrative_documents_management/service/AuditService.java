package org.woridge.ms_administrative_documents_management.service;

public interface AuditService {
     void log(String action, String target, String status);
}
