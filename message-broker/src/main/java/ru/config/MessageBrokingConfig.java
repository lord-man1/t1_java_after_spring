package ru.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.InMemoryMessageBroker;
import ru.MessageBroker;
import ru.SubscriberBeanPostProcessor;
import ru.SubscriberContainer;

@ConditionalOnProperty(prefix = "spring.application.messaging", name = "enabled", havingValue = "true")
@Configuration
@EnableConfigurationProperties(MessageBrokingConfigProperties.class)
public class MessageBrokingConfig {
    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean
    public static SubscriberBeanPostProcessor subscriberBeanPostProcessor() {
        return new SubscriberBeanPostProcessor();
    }

    @Bean
    public MessageBroker pubSubChannel() {
        return new InMemoryMessageBroker();
    }

    @Bean
    public SubscriberContainer subscriberContainer(MessageBroker broker, ObjectMapper mapper) {
        return new SubscriberContainer(broker, mapper);
    }
}
