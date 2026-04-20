package org.woridge.ms_administrative_documents_management.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.woridge.ms_administrative_documents_management.exception.template.TemplateNotFoundException;

import java.util.Arrays;

@AllArgsConstructor
@Getter
public enum DocumentTemplateType {

    WORK_CERTIFICATE("work-certificate"),
    SALARY_CERTIFICATE("salary-certificate"),
    MISSION_ORDER("mission-order");

    private final String fileName;

    public static DocumentTemplateType findByTemplateCode(String templateCode) {

        return Arrays
                .stream(values())
                .filter(documentTemplateType ->
                        documentTemplateType
                                .name()
                                .equalsIgnoreCase(templateCode)
                )
                .findFirst()
                .orElseThrow(() ->
                        new TemplateNotFoundException("No template found for code: " + templateCode)
                );
    }
}
