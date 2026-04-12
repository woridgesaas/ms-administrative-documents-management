package org.woridge.ms_administrative_documents_management.messaging.publisher;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import org.woridge.ms_administrative_documents_management.messaging.event.DocumentRequestCreatedEvent;
import org.woridge.ms_administrative_documents_management.properties.RabbitMQProperties;

@Slf4j
@Component
@RequiredArgsConstructor
public class DocumentRequestEventPublisher {

    private final RabbitTemplate rabbitTemplate;
    private final RabbitMQProperties rabbitMQProperties;

    public void publish(DocumentRequestCreatedEvent event) {
        rabbitTemplate.convertAndSend(
                rabbitMQProperties.getExchange(),
                rabbitMQProperties.getRouting().getDocumentRequestCreated(),
                event
        );

        log.info("Published document request created event for employeeCin={}, documentCode={}",
                event.employeeCin(), event.documentCode());
    }
}