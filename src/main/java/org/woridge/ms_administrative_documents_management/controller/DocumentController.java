package org.woridge.ms_administrative_documents_management.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.*;

import org.springframework.web.bind.annotation.*;
import org.woridge.ms_administrative_documents_management.dto.CreateDocumentRequest;
import org.woridge.ms_administrative_documents_management.dto.DocumentRequestResponse;
import org.woridge.ms_administrative_documents_management.dto.GetDocumentRequest;
import org.woridge.ms_administrative_documents_management.service.DocumentRequestService;
import org.woridge.ms_administrative_documents_management.service.DocumentService;

import java.util.List;

@RestController
@RequestMapping("/requests")
@AllArgsConstructor
public class DocumentController {

    private final DocumentRequestService service;
    private final DocumentService documentService;

    @PostMapping
    public ResponseEntity<DocumentRequestResponse> create(
            @Valid @RequestBody CreateDocumentRequest request,
            @RequestHeader("X-User-CIN") String cin
    ) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.createDocumentRequest(request, cin));
    }

    @GetMapping
    public ResponseEntity<List<DocumentRequestResponse>> getMyRequests(
            @RequestHeader("X-User-CIN") String cin
    ) {
        return ResponseEntity.ok(service.getEmployeeDocumentRequests(cin));
    }

    @GetMapping("/documents")
    public ResponseEntity<List<GetDocumentRequest>> getMyDocuments(
            @RequestHeader("X-User-CIN") String cin
    ) {
        return ResponseEntity.ok(documentService.getEmployeeDocuments(cin));
    }

    /*@GetMapping("/{id}/download")
    public ResponseEntity<Resource> downloadDocument(
            @PathVariable UUID id,
            @RequestHeader("X-User-CIN") String cin
    ) {
        Resource resource = documentService.downloadDocument(id, cin);

        String filename = resource.getFilename() != null
                ? resource.getFilename()
                : "document_" + id + ".pdf";

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + filename + "\"")
                .contentType(MediaType.APPLICATION_PDF)
                .body(resource);
    }*/
}