package com.javaAdvance.mq.kafka.web;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Properties;

/**
 * @author hitopei
 * create by hitopei on 2021/1/13 2:02 下午
 */
@RestController
public class ProducerController {
//    @Resource
//    private KafkaTemplate<Object, Object> kafkaTemplate;

    @RequestMapping("produce")
    public String produce() {
        //kafkaTemplate.send("testk", "hello");

        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", "localhost:9000, localhost:9001, localhost:9002");
        properties.setProperty("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.setProperty("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        KafkaProducer kafkaProducer = new KafkaProducer(properties);
        ProducerRecord record = new ProducerRecord("test-cluster", "1234 cluster");
        kafkaProducer.send(record);
        kafkaProducer.close();
        return "success";
    }
}
