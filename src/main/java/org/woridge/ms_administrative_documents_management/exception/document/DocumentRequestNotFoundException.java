
package org.woridge.ms_administrative_documents_management.exception.document;

public class DocumentRequestNotFoundException extends RuntimeException {
    public DocumentRequestNotFoundException(String message) {
        super(message);
    }
}