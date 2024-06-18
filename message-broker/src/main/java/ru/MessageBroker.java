package ru;

public interface MessageBroker {
    void publish(String message);

    String poll() throws InterruptedException;
}
