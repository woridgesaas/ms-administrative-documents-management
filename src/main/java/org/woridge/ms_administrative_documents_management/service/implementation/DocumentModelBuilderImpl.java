package org.woridge.ms_administrative_documents_management.service.implementation;

import org.springframework.stereotype.Service;
import org.woridge.ms_administrative_documents_management.enumeration.DocumentTemplateType;
import org.woridge.ms_administrative_documents_management.service.DocumentModelBuilder;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Service
public class DocumentModelBuilderImpl implements DocumentModelBuilder {

    @Override
    public Map<String, Object> buildDocumentModel(DocumentTemplateType documentTemplateType, String employeeCIN) {

        // TODO: Replace with real remote call to ms internal users management and attendance

        return switch (documentTemplateType) {

            case WORK_CERTIFICATE -> buildWorkCertificateModel(employeeCIN);
            case SALARY_CERTIFICATE -> buildSalaryCertificateModel(employeeCIN);
            case MISSION_ORDER -> buildMissionOrderModel(employeeCIN);
        };
    }

    private Map<String, Object> buildWorkCertificateModel(String cin) {

        Map<String, Object> model = new HashMap<>();

        model.put("employeeName", "John Doe");
        model.put("cin", cin);
        model.put("position", "Software Engineer");
        model.put("department", "IT");
        model.put("startDate", LocalDate.of(2021, 3, 1));
        model.put("issueDate", LocalDate.now());
        return model;
    }

    private Map<String, Object> buildSalaryCertificateModel(String cin) {

        Map<String, Object> model = new HashMap<>();

        model.put("employeeName", "John Doe");
        model.put("cin", cin);
        model.put("position", "Software Engineer");
        model.put("netSalary", 8500.00);
        model.put("issueDate", LocalDate.now());
        return model;
    }

    private Map<String, Object> buildMissionOrderModel(String cin) {

        Map<String, Object> model = new HashMap<>();

        model.put("employeeName", "John Doe");
        model.put("cin", cin);
        model.put("destination", "Casablanca");
        model.put("missionStartDate", LocalDate.now());
        model.put("missionEndDate", LocalDate.now().plusDays(3));
        model.put("issueDate", LocalDate.now());
        return model;
    }
}
