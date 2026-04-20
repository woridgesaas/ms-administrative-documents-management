package org.woridge.ms_administrative_documents_management.exception.request;

public class MissingSubscriptionStatusInRequestHeaderException extends RuntimeException {

    public MissingSubscriptionStatusInRequestHeaderException(String message) {

        super(message);
    }
}
