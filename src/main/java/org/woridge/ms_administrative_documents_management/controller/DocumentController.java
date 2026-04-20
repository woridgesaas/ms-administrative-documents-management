package org.woridge.ms_administrative_documents_management.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.woridge.ms_administrative_documents_management.dto.CreateDocumentRequest;
import org.woridge.ms_administrative_documents_management.dto.DocumentRequestResponse;
import org.woridge.ms_administrative_documents_management.service.DocumentRequestService;

import java.util.List;

@RestController
@RequestMapping("/requests")
@AllArgsConstructor
public class DocumentController {

    private final DocumentRequestService service;

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
}