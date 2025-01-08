package com.fly.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class KafkaMessageProducer {

    public static void main(String[] args) {
        Properties props = new Properties();
        props.put("bootstrap.servers", "10.19.201.192:9092");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer"); // Key 序列化器
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer"); // Value 序列化器
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);

        try (Producer<String, String> producer = new KafkaProducer<>(props)) {
            for (int i = 0; i < 100000; i++) {
                producer.send(new ProducerRecord<>("test-topic", "key-" + i, "hello-" + i));
            }
        }
        System.out.println("Producer send message success");
    }
}
