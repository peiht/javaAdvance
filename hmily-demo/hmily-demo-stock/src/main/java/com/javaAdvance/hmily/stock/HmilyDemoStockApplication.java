package com.javaAdvance.hmily.stock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author hitopei
 *
 * hmily stock
 */
@SpringBootApplication(exclude = {MongoAutoConfiguration.class})
@EnableEurekaClient
public class HmilyDemoStockApplication {
    public static void main(String[] args) {
        SpringApplication.run(HmilyDemoStockApplication.class, args);
    }
}
