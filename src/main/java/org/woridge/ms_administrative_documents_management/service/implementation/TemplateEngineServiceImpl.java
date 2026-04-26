package org.woridge.ms_administrative_documents_management.service.implementation;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import org.thymeleaf.exceptions.TemplateInputException;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.woridge.ms_administrative_documents_management.dto.TenantInfo;
import org.woridge.ms_administrative_documents_management.enumeration.DocumentTemplateType;
import org.woridge.ms_administrative_documents_management.exception.template.TemplateNotFoundException;
import org.woridge.ms_administrative_documents_management.service.TemplateEngineService;
import org.thymeleaf.context.Context;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor

public class TemplateEngineServiceImpl implements TemplateEngineService {

    @Autowired
    private final SpringTemplateEngine templateEngine;

    @Value("${ressource.templates.docs-path}")
    private String docsPath;


    private final TenantInfo tenantInfo;
    /**
     * Render a template with the given data
     * @param templateName name of template in /templates/docs/ (ex: "mission-order")
     * @param data Map that hold data like (${tenantName}, ${transportModes}...)
     * @return Compiled HTML ,String can used to generate PDF
     */
    @Override
    public String render(DocumentTemplateType templateName, Map<String, Object> data) {
        Map<String, Object> finalModel = new HashMap<>();

        finalModel.put("tenantName", tenantInfo.getTenantName());
        System.out.println("rrrrrrrrrrrrrrrrrrr "+tenantInfo.getLogoUrl());
        finalModel.put("logoUrl", tenantInfo.getLogoUrl());
        finalModel.put("address", tenantInfo.getAddress());
        finalModel.put("ice", tenantInfo.getIce());
        finalModel.put("rc", tenantInfo.getRc());
        finalModel.put("phone", tenantInfo.getPhone());
        finalModel.put("tenantCity", tenantInfo.getCity());
        finalModel.put("cnssNumber", tenantInfo.getCnss());

        //Merge employee data with teanntInfos :
        finalModel.putAll(data);
        try {
            Context context = new Context();
            context.setVariables(finalModel);
            String cleanName = templateName.getFileName().replace(".html", "");
            String fullPath = docsPath + cleanName;

            return templateEngine.process(fullPath, context);

        } catch (TemplateInputException ex) {
            System.err.println("[ERROR] Template not found: " + templateName);
            throw new TemplateNotFoundException("Document with this name '" + templateName + "' not found.");
        } catch (Exception ex) {

            throw new TemplateNotFoundException("Error whiile processing the document rendring : " + ex.getMessage());
        }
    }
}
