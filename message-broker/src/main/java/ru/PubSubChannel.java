package ru;

public interface  PubSubChannel<T> {
    void publish(T message);

    T poll();
}
