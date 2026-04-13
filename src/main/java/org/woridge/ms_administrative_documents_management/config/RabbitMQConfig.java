package org.woridge.ms_administrative_documents_management.config;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.woridge.ms_administrative_documents_management.properties.RabbitMQProperties;

@Configuration
@RequiredArgsConstructor
public class RabbitMQConfig {

    private final RabbitMQProperties props;

    // ===== QUEUES =====
    @Bean
    public Queue userCreatedQueue() {
        return QueueBuilder.durable("documents.user.created.queue").build();
    }

    @Bean
    public Queue documentRequestQueue() {
        return QueueBuilder.durable("document.request.queue").build();
    }

    // ===== EXCHANGE =====
    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(props.getExchange());
    }

    // ===== BINDINGS =====
    @Bean
    public Binding documentRequestBinding() {
        return BindingBuilder.bind(documentRequestQueue())
                .to(exchange())
                .with(props.getRouting().getDocumentRequestCreated());
    }

    @Bean
    public Binding userCreatedBinding() {
        return BindingBuilder.bind(userCreatedQueue())
                .to(exchange())
                .with("user.created");
    }

    // ===== CONVERTER =====
    @Bean
    public MessageConverter messageConverter() {
        return new JacksonJsonMessageConverter();
    }

    // ===== LISTENER =====
    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(
            ConnectionFactory connectionFactory,
            MessageConverter converter
    ) {
        var factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(converter);
        return factory;
    }
}