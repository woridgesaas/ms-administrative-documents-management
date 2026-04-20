package org.woridge.ms_administrative_documents_management.service.implementation;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.woridge.ms_administrative_documents_management.enumeration.DocumentTemplateType;
import org.woridge.ms_administrative_documents_management.model.DocumentRequest;
import org.woridge.ms_administrative_documents_management.service.*;
import org.woridge.ms_administrative_documents_management.utils.FileUtils;

import java.util.Map;

@Service
@AllArgsConstructor
public class DocumentGeneratorServiceImpl implements DocumentGeneratorService {

    private final TemplateEngineService templateEngineService;
    private final MinioService minioService;
    private final DocumentConversionService documentConversionService;
    private final DocumentService documentService;
    private final DocumentModelBuilder documentModelBuilder;

    @Override
    public void generateAndStoreDocument(DocumentRequest documentRequest, String tenantName) {

        String templateCode = documentRequest.getType().getTemplateCode();
        String employeeCIN = documentRequest.getUser().getCin();

        // Resolve document template type
        DocumentTemplateType documentTemplateType = DocumentTemplateType.findByTemplateCode(templateCode);

        // Build the document model
        Map<String, Object> documentModel = documentModelBuilder.buildDocumentModel(documentTemplateType, employeeCIN);

        // Render HTML from Thymeleaf template
        String html = templateEngineService.render(documentTemplateType, documentModel);

        // Convert rendered HTML to PDF bytes
        byte[] pdfBytes = documentConversionService.convertHtmlToPdf(html);

        // Build unique file name
        String fileName = FileUtils.buildPdfFileName(templateCode, employeeCIN);

        // Upload to MinIO and get storage path
        String documentPath = minioService.uploadPdfDocument(tenantName, fileName, pdfBytes);

        // Persist the document record
        documentService.addDocument(documentRequest, documentPath);
    }
}