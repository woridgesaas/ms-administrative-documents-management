package org.woridge.ms_administrative_documents_management.service;

public interface DocumentConversionService {

    byte[] convertHtmlToPdf(String html);
}
