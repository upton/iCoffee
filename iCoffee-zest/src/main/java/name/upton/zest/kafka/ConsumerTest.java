package name.upton.zest.kafka;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;
import kafka.message.MessageAndMetadata;

public class ConsumerTest {

    public static void main(String[] args) {
        // 首先生成配置信息
        Properties props = new Properties();
        props.put("zookeeper.connect", "127.0.0.1:2181");
        props.put("zookeeper.connectiontimeout.ms", "5000");
        props.put("group.id", "AAA-BBB");
        // The frequency in ms that the consumer offsets are committed to zookeeper
        props.put("auto.commit.interval.ms", "30000");

        // 连接到kafka集群（既是连接到ZK集群）
        ConsumerConfig consumerConfig = new ConsumerConfig(props);
        ConsumerConnector connector = Consumer.createJavaConsumerConnector(consumerConfig);

        Map<String, Integer> topics = new HashMap<String, Integer>();
        topics.put("AAA", 2);
        topics.put("BBB", 2);

        final ExecutorService aaaExecutor = Executors.newFixedThreadPool(2, new ThreadFactory() {
            private AtomicInteger counter = new AtomicInteger(0);

            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r, "Kafka-AAA-Topic-Stream-Thread-" + counter.getAndIncrement());
            }
        });

        final ExecutorService bbbExecutor = Executors.newFixedThreadPool(2, new ThreadFactory() {
            private AtomicInteger counter = new AtomicInteger(0);

            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r, "Kafka-BBB-Topic-Stream-Thread-" + counter.getAndIncrement());
            }
        });

        // 创建数据流
        Map<String, List<KafkaStream<byte[], byte[]>>> topicStreams = connector.createMessageStreams(topics);

        List<KafkaStream<byte[], byte[]>> aaaStreams = topicStreams.get("AAA");
        for (final KafkaStream<byte[], byte[]> stream : aaaStreams) {
            MyConsumer consumer = new MyConsumer(stream);
            aaaExecutor.submit(consumer);
        }

        // 状态消息的数据流
        List<KafkaStream<byte[], byte[]>> bbbStreams = topicStreams.get("BBB");
        for (final KafkaStream<byte[], byte[]> stream : bbbStreams) {
            MyConsumer consumer = new MyConsumer(stream);
            bbbExecutor.submit(consumer);
        }

        // 系统退出时让线程池停止
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                aaaExecutor.shutdown();
                bbbExecutor.shutdown();
            }
        }));
    }

}

class MyConsumer implements Runnable {
    private static AtomicInteger count = new AtomicInteger(0);
    
    private KafkaStream<byte[], byte[]> stream;

    public MyConsumer(final KafkaStream<byte[], byte[]> stream) {
        this.stream = stream;
    }

    @Override
    public void run() {
        for (MessageAndMetadata<byte[], byte[]> msgAndMetadata : stream) {
            try {
                String message = new String(msgAndMetadata.message(), "UTF-8");
                String key = new String(msgAndMetadata.key(), "UTF-8");

                System.out.println("key : " + key + " , message : " + message + " , count : " + count.incrementAndGet());
                
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}