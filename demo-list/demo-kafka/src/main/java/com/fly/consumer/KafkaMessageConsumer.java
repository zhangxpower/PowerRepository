package com.fly.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Collections;
import java.util.Properties;

public class KafkaMessageConsumer {

    public static void main(String[] args) {
        Properties props = new Properties();
        props.put("bootstrap.servers", "10.19.201.192:9092");
        props.put("group.id", "test-group"); // 消费者组 ID
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer"); // Key 反序列化器
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer"); // Value 反序列化器
        props.put("enable.auto.commit", "true"); // 自动提交偏移量
        props.put("auto.commit.interval.ms", "1000"); // 自动提交的间隔时间
        props.put("max.poll.records", "1000");

        try (KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props)) {
            consumer.subscribe(Collections.singleton("test-topic"));
            for (;;) {
                ConsumerRecords<String, String> records = consumer.poll(100);
                if (records.count() > 0) {
                    for (ConsumerRecord<String, String> record : records) {
                        System.out.println(record.value());
                    }
                }
            }
        }
    }
}
