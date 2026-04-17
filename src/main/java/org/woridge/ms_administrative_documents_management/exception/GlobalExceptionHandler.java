package org.woridge.ms_administrative_documents_management.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.woridge.ms_administrative_documents_management.dto.ErrorResponse;
import org.woridge.ms_administrative_documents_management.enumeration.ErrorCode;
import org.woridge.ms_administrative_documents_management.exception.document.DocumentRequestNotFoundException;
import org.woridge.ms_administrative_documents_management.exception.document.DocumentTypeNotFoundException;
import org.woridge.ms_administrative_documents_management.exception.request.MissingCinInRequestHeaderException;
import org.woridge.ms_administrative_documents_management.exception.request.MissingRoleInRequestHeaderException;
import org.woridge.ms_administrative_documents_management.exception.template.TemplateNotFoundException;
import org.woridge.ms_administrative_documents_management.exception.user.UserNotFoundException;

import java.util.List;

@RestControllerAdvice
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

    // Request exceptions
    @ExceptionHandler(MissingCinInRequestHeaderException.class)
    public ResponseEntity<ErrorResponse<String>> handleMissingCinInRequestHeaderException(MissingCinInRequestHeaderException exception) {

        return ResponseEntity
                .status(HttpStatus.BAD_GATEWAY)
                .body(
                        ErrorResponse.createErrorResponse(
                                exception.getMessage(),
                                ErrorCode.MISSING_GATEWAY_FORWARDED_HEADER
                        )
                );
    }

    @ExceptionHandler(MissingRoleInRequestHeaderException.class)
    public ResponseEntity<ErrorResponse<String>> handleMissingRoleInRequestHeaderException(MissingRoleInRequestHeaderException exception) {

        return ResponseEntity
                .status(HttpStatus.BAD_GATEWAY)
                .body(
                        ErrorResponse.createErrorResponse(
                                exception.getMessage(),
                                ErrorCode.MISSING_GATEWAY_FORWARDED_HEADER
                        )
                );
    }

    // System exceptions
    // Request validation exception (@Valid)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse<List<String>>> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {

        List<String> errorList = exception
                .getBindingResult()
                .getFieldErrors()
                .stream()
                .map(FieldError::getDefaultMessage)
                .toList();

        return ResponseEntity
                .status(HttpStatus.UNPROCESSABLE_CONTENT)
                .body(
                        ErrorResponse.createErrorResponse(
                                errorList,
                                ErrorCode.REQUEST_VALIDATION_FAILED
                        )
                );
    }

    // Enum deserialization exception
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse<String>> handleHttpMessageNotReadableException(HttpMessageNotReadableException exception) {

        return ResponseEntity
                .status(HttpStatus.UNPROCESSABLE_CONTENT)
                .body(
                        ErrorResponse.createErrorResponse(
                                "Invalid enum value",
                                ErrorCode.INVALID_ENUM_VALUE
                        )
                );
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse<String>> handleIllegalArgumentException(IllegalArgumentException exception) {

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(
                        ErrorResponse.createErrorResponse(
                                exception.getMessage(),
                                ErrorCode.INVALID_ARGUMENT
                        )
                );
    }
}
