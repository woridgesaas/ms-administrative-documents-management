package org.woridge.ms_administrative_documents_management.exception.minio;

public class MinioUploadPdfDocumentFailedException extends RuntimeException {

    public MinioUploadPdfDocumentFailedException(String message) {

        super(message);
    }
}
