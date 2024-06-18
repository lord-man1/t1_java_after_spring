package ru;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Publisher<T> {
    private final MessageBroker messageBroker;
    private final ObjectMapper mapper;

    public Publisher(MessageBroker messageBroker, ObjectMapper mapper) {
        this.messageBroker = messageBroker;
        this.mapper = mapper;
    }

    public void publish(T message) throws JsonProcessingException {
        messageBroker.publish(serializeToJson(message));
    }

    private String serializeToJson(T obj) throws JsonProcessingException {
        return mapper.writeValueAsString(obj);
    }
}
