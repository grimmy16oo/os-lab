import java.util.concurrent.Semaphore;

class Buffer {
    int item;
    Semaphore empty = new Semaphore(1);
    Semaphore full = new Semaphore(0);

    public void produce(int value) throws InterruptedException {
        empty.acquire();

        item = value;
        System.out.println("Produced: " + item);

        full.release();
    }

    public void consume() throws InterruptedException {
        full.acquire();

        System.out.println("Consumed: " + item);

        empty.release();
    }
}

class Producer extends Thread {
    Buffer buffer;

    Producer(Buffer b) {
        buffer = b;
    }

    public void run() {
        try {
            for (int i = 1; i <= 5; i++)
                buffer.produce(i);
        } catch (Exception e) {}
    }
}

class Consumer extends Thread {
    Buffer buffer;

    Consumer(Buffer b) {
        buffer = b;
    }

    public void run() {
        try {
            for (int i = 1; i <= 5; i++)
                buffer.consume();
        } catch (Exception e) {}
    }
}

public class ProducerConsumer {
    public static void main(String args[]) {
        Buffer b = new Buffer();

        new Producer(b).start();
        new Consumer(b).start();
    }
}