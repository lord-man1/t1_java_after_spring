package ru;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class InMemoryMessageBroker implements MessageBroker {
    private final BlockingQueue<String> phraseQueue = new LinkedBlockingQueue<>();

    @Override
    public void publish(String message) {
        phraseQueue.add(message);
    }

    @Override
    public String poll() throws InterruptedException {
        return phraseQueue.take();
    }
}
