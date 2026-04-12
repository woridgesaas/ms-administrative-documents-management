package org.woridge.ms_administrative_documents_management.messaging.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.woridge.ms_administrative_documents_management.messaging.event.UserCreatedForDocumentEvent;
import org.woridge.ms_administrative_documents_management.model.User;
import org.woridge.ms_administrative_documents_management.repository.UserRepository;

import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class UserDocumentConsumer {

    private final UserRepository userRepository;

    @RabbitListener(queues = "documents.user.created.queue")
    public void consume(UserCreatedForDocumentEvent event) {

        log.info("Received user event → {}", event);

        // 🔥 1. VALIDATION EVENT (OBLIGATOIRE)
        if (event == null) {
            log.error("Received null event");
            return;
        }

        if (event.getCin() == null || event.getCin().isBlank()) {
            log.error("Invalid event: CIN is missing → {}", event);
            return;
        }

        if (event.getId() == null || event.getId().isBlank()) {
            log.error("Invalid event: ID is missing → {}", event);
            return;
        }

        //  2. DUPLICATE CHECK
        if (userRepository.existsByCin(event.getCin())) {
            log.warn("User already exists: {}", event.getCin());
            return;
        }

        try {
            UUID userId = UUID.fromString(event.getId());

            User user = User.builder()
                    .id(userId)
                    .cin(event.getCin())
                    .build();

            userRepository.save(user);

            log.info("User saved successfully: {}", event.getCin());

        } catch (IllegalArgumentException e) {
            log.error("Invalid UUID format → {}", event.getId(), e);
        } catch (Exception e) {
            log.error("Unexpected error while processing event", e);
        }
    }
}