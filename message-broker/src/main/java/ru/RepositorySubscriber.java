package ru;

public class RepositorySubscriber<T> implements Runnable {
    private final PubSubChannel<T> pubSubChannel;
    private final Repository<T> repository;

    public RepositorySubscriber(PubSubChannel<T> pubSubChannel, Repository<T> repository) {
        this.pubSubChannel = pubSubChannel;
        this.repository = repository;
    }

    @Override
    @Subscriber
    public void run() {
        while (true) {
            T newPhrase = pubSubChannel.poll();
            if (newPhrase != null) {
                repository.save(newPhrase);
            } else {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.out.println("Message is null");
                }
            }
        }
    }
}
