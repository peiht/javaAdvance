package com.javaAdvance.hmily.order;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author hitopei
 *
 * hmily order
 */
@EnableEurekaClient
@SpringBootApplication(exclude = {MongoAutoConfiguration.class})
@MapperScan(basePackages = "com.javaAdvance.hmily.order.repository.mysql.mapper")
public class HmilyDemoOrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(HmilyDemoOrderApplication.class, args);
    }
}
