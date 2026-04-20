package org.woridge.ms_administrative_documents_management.exception.minio;

public class MinioBucketCreationFailedException extends RuntimeException {

    public MinioBucketCreationFailedException(String message) {

        super(message);
    }
}
