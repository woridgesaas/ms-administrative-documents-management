package org.woridge.ms_administrative_documents_management.enumeration;

public enum ErrorCode {
    // Gateway
    MISSING_GATEWAY_FORWARDED_HEADER,
    REQUEST_NOT_ROUTED_THROUGH_GATEWAY,

    // Request validation (@Valid)
    REQUEST_VALIDATION_FAILED,

    // Illegal argument
    INVALID_ARGUMENT,

    // Enumeration value
    INVALID_ENUM_VALUE,

    // Minio
    MINIO_BUCKET_CHECK_FAILED,
    MINIO_BUCKET_CREATION_FAILED,
    MINIO_UPLOAD_FAILED,

    // Document
    DOCUMENT_CONVERSION_FAILED
}
