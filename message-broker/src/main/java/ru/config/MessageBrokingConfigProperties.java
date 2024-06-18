package ru.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring.application.messaging")
public record MessageBrokingConfigProperties(boolean enabled) {
}
