package org.woridge.ms_administrative_documents_management.enumeration;

import lombok.Getter;
@Getter
public enum DocumentTemplateType {
    WORK_CERTIFICATE("work-certificate"),
    SALARY_CERTIFICATE("salary-certificate"),
    MISSION_ORDER("mission-order");

    private final String fileName;


    DocumentTemplateType(String fileName) {
        this.fileName = fileName;
    }

}
