package org.woridge.ms_administrative_documents_management.utils;

import java.util.UUID;

public final class FileUtils {

    private FileUtils() {

        throw new UnsupportedOperationException("You can't instantiate file utils object");
    }

    // Builds a unique PDF file name (templateCode_employeeCIN_uuid.pdf)
    public static String buildPdfFileName(String templateCode, String employeeCIN) {

        return templateCode + "_" + employeeCIN + "_" + UUID.randomUUID() + ".pdf";
    }
}
