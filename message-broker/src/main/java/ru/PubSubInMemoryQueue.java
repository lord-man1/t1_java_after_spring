package ru;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class PubSubInMemoryQueue<T> implements PubSubChannel<T> {
    private final BlockingQueue<T> phraseQueue = new LinkedBlockingQueue<>();

    @Override
    public void publish(T message) {
        phraseQueue.add(message);
    }

    @Override
    public T poll() {
        return phraseQueue.poll();
    }
}
