package name.upton.zest.kafka;

import java.util.Properties;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

public class ProducerTest {

    public static void main(String[] args) throws InterruptedException {
        Thread a1 = new Thread(new MyProducer("AAA"), "Thread-AAA-1");
        Thread a2 = new Thread(new MyProducer("AAA"), "Thread-AAA-2");
        Thread b1 = new Thread(new MyProducer("BBB"), "Thread-BBB-1");
        Thread b2 = new Thread(new MyProducer("BBB"), "Thread-BBB-2");
        
        a1.start();
        a2.start();
        b1.start();
        b2.start();
        
        a1.join();
        a2.join();
        b1.join();
        b2.join();
    }
}

class MyProducer implements Runnable {
    private static AtomicInteger count = new AtomicInteger(0);
    private Producer<byte[], byte[]> producer;

    /**
     * kafka主题名
     */
    private String topicName;

    public MyProducer(String topicName) {
        this.topicName = topicName;
        Properties producerProps = new Properties();

        producerProps.put("metadata.broker.list", "127.0.0.1:9092,127.0.0.1:9192");
        producerProps.put("request.required.acks", "1");
        producerProps.put("request.timeout.ms", "2000");
        producerProps.put("producer.type", "sync");

        ProducerConfig config = new ProducerConfig(producerProps);

        this.producer = new Producer<byte[], byte[]>(config);
    }

    @Override
    public void run() {
        Random rd = new Random();

        while (true) {
            try {
                byte[] msgBytes = String.valueOf(topicName + "主题-" + rd.nextDouble()).getBytes("UTF-8");
                byte[] keyBytes = String.valueOf(topicName + "折扣-" + rd.nextInt()).getBytes("UTF-8");

                KeyedMessage<byte[], byte[]> message = new KeyedMessage<byte[], byte[]>(this.topicName, keyBytes, msgBytes);

                producer.send(message);
                
                System.out.println(topicName +" Producer count : " + count.incrementAndGet());

                Thread.sleep(2000L);
            } catch (Exception e) {
                System.out.println("producer.send error");
                e.printStackTrace();
                throw new RuntimeException("ERROR");
            }
        }
    }
}