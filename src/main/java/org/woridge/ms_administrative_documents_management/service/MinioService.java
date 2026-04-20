package org.woridge.ms_administrative_documents_management.service;

public interface MinioService {

    String uploadPdfDocument(String tenantName, String fileName, byte[] content);

    // Resource downloadFile(String objectName);
}