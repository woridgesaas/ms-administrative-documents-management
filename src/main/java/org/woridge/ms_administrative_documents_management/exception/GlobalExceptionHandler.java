package org.woridge.ms_administrative_documents_management.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.woridge.ms_administrative_documents_management.exception.document.DocumentRequestNotFoundException;
import org.woridge.ms_administrative_documents_management.exception.document.DocumentTypeNotFoundException;
import org.woridge.ms_administrative_documents_management.exception.template.TemplateNotFoundException;
import org.woridge.ms_administrative_documents_management.exception.user.UserNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(TemplateNotFoundException.class)
    public ResponseEntity<String> handleTemplateError(TemplateNotFoundException ex) {

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
    @ExceptionHandler(DocumentTypeNotFoundException.class)
    public ResponseEntity<String> handleDocumentTypeNotFoundException(DocumentTypeNotFoundException ex) {

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFoundException(UserNotFoundException ex) {

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }


    @ExceptionHandler(DocumentRequestNotFoundException.class)
    public ResponseEntity<String> handleDocumentRequestNotFoundException(
            DocumentRequestNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
