package org.woridge.ms_administrative_documents_management.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.woridge.ms_administrative_documents_management.dto.DocumentTypeResponse;
import org.woridge.ms_administrative_documents_management.service.AuditService;
import org.woridge.ms_administrative_documents_management.service.DocumentTypeService;

import java.util.List;

@RestController
@RequestMapping("/types")
@AllArgsConstructor
public class DocumentTypeController {

    private final AuditService auditService;
    private final DocumentTypeService documentTypeService;

    @GetMapping
    public ResponseEntity<List<DocumentTypeResponse>> getAllTypes() {
        this.auditService.log("dirchihaja" , "idk" , "udkdk");
        return ResponseEntity.ok(documentTypeService.getAllTypes());
    }
}