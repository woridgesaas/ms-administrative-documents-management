package org.woridge.ms_administrative_documents_management.exception.minio;

public class MinioBucketCheckFailedException extends RuntimeException {

    public MinioBucketCheckFailedException(String message) {

        super(message);
    }
}
