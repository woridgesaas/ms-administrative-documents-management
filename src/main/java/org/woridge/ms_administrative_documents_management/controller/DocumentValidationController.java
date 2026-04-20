package org.woridge.ms_administrative_documents_management.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.woridge.ms_administrative_documents_management.dto.DocumentRequestResponse;
import org.woridge.ms_administrative_documents_management.dto.DocumentValidationResponse;
import org.woridge.ms_administrative_documents_management.enumeration.ValidationAction;
import org.woridge.ms_administrative_documents_management.service.DocumentValidationService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/requests")
@RequiredArgsConstructor
public class DocumentValidationController {

    private final DocumentValidationService validationService;

    // RH voit toutes les demandes
    // GET /api/documents/requests/all
    @GetMapping("/all")
    public ResponseEntity<List<DocumentRequestResponse>> getAllRequests() {
        return ResponseEntity.ok(validationService.getAllRequests());
    }

    // RH approuve une demande
    // PUT /api/documents/requests/{id}/approve
    @PutMapping("/{id}/approve")
    public ResponseEntity<DocumentValidationResponse> approve(
            @PathVariable UUID id,
            @RequestHeader("X-User-CIN") String rhCin
    ) {
        return ResponseEntity.ok(
                validationService.validate(id, ValidationAction.APPROVE, rhCin)
        );
    }

    //  RH rejette une demande
    // PUT /api/documents/requests/{id}/reject
    @PutMapping("/{id}/reject")
    public ResponseEntity<DocumentValidationResponse> reject(
            @PathVariable UUID id,
            @RequestHeader("X-User-CIN") String rhCin
    ) {
        return ResponseEntity.ok(
                validationService.validate(id, ValidationAction.REJECT, rhCin)
        );
    }
}