package org.woridge.ms_administrative_documents_management.service;

public interface TenantProvisioningService {
    void runMigrationsForTenant(String sub , String serviceKey);

}
