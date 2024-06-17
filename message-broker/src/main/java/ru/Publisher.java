package ru;

public class Publisher<T> {
    private final PubSubChannel<T> pubSubChannel;

    public Publisher(PubSubChannel<T> pubSubChannel) {
        this.pubSubChannel = pubSubChannel;
    }

    public void publish(T message) {
        pubSubChannel.publish(message);
    }
}
