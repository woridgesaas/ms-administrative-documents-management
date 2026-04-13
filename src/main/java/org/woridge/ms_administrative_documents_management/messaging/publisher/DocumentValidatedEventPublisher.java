// src/main/java/.../messaging/publisher/DocumentValidatedEventPublisher.java
package org.woridge.ms_administrative_documents_management.messaging.publisher;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import org.woridge.ms_administrative_documents_management.messaging.event.DocumentValidatedEvent;
import org.woridge.ms_administrative_documents_management.properties.RabbitMQProperties;

@Slf4j
@Component
@RequiredArgsConstructor
public class DocumentValidatedEventPublisher {

    private final RabbitTemplate rabbitTemplate;
    private final RabbitMQProperties rabbitMQProperties;

    public void publish(DocumentValidatedEvent event) {
        rabbitTemplate.convertAndSend(
                rabbitMQProperties.getExchange(),
                rabbitMQProperties.getRouting().getDocumentValidated(), // ← nouvelle routing key
                event
        );

        log.info("Published document validated event for employeeCin={}, status={}",
                event.employeeCin(), event.status());
    }
}