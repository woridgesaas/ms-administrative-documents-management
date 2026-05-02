package org.woridge.ms_administrative_documents_management.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "rabbitmq")
@Getter
@Setter
public class RabbitMQProperties {

    private String exchange;
    private Routing routing;

    @Getter
    @Setter
    public static class Routing {
        private String documentRequestCreated;
        private String documentValidated;
        private String tenantReady;
    }
}