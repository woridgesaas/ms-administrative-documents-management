package org.woridge.ms_administrative_documents_management.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.woridge.ms_administrative_documents_management.dto.DocumentTypeResponse;
import org.woridge.ms_administrative_documents_management.service.DocumentTypeService;

import java.util.List;

@RestController
@RequestMapping("/types")
@RequiredArgsConstructor
public class DocumentTypeController {

    private final DocumentTypeService documentTypeService;
    @GetMapping
    public ResponseEntity<List<DocumentTypeResponse>> getAllTypes() {

        return ResponseEntity.ok(documentTypeService.getAllTypes());
    }
}