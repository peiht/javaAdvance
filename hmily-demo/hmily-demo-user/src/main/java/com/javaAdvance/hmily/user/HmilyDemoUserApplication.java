package com.javaAdvance.hmily.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author hitopei
 *
 * hmily user
 */
@EnableEurekaClient
@SpringBootApplication
public class HmilyDemoUserApplication {
    public static void main(String[] args) {
        SpringApplication.run(HmilyDemoUserApplication.class, args);
    }
}