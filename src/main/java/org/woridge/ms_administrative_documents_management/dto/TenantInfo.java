package org.woridge.ms_administrative_documents_management.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
@ConfigurationProperties(prefix = "ressource.mock-tenant")
public class TenantInfo {
    private String tenantName;
    private String logoUrl;
    private String address;
    private String city;
    private String ice;
    private String rc;
    private String cnss;
    private String phone;
}
