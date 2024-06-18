package ru;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SubscriberContainer {
    private final MessageBroker messageBroker;
    private final ObjectMapper mapper;
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    public SubscriberContainer(MessageBroker messageBroker, ObjectMapper mapper) {
        this.messageBroker = messageBroker;
        this.mapper = mapper;
    }

    @PostConstruct
    public void init() {
        SubscriberBeanPostProcessor.METHOD_2_BEAN.forEach((method, o) -> {
            executorService.execute(() -> {
                try {
                    var message = deserializeMessageTo(
                            method.getParameters()[0].getType()
                    );
                    method.invoke(o, message);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
        });
    }

    private <T> T deserializeMessageTo(Class<T> clazz) throws InterruptedException, JsonProcessingException {
        return mapper.readValue(messageBroker.poll(), clazz);
    }
}
