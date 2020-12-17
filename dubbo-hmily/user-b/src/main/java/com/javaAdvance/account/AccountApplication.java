package com.javaAdvance.account;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author hitopei
 * 启动类
 */
@SpringBootApplication
@MapperScan({"com.javaAdvance.account.api.repository.mysql.mapper"})
public class AccountApplication {
    public static void main(String[] args) {
        SpringApplication.run(AccountApplication.class, args);
    }
}
