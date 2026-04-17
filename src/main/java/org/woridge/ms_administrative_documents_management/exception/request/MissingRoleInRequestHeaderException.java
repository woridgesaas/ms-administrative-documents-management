package org.woridge.ms_administrative_documents_management.exception.request;

public class MissingRoleInRequestHeaderException extends RuntimeException {

    public MissingRoleInRequestHeaderException(String message) {

        super(message);
    }
}
