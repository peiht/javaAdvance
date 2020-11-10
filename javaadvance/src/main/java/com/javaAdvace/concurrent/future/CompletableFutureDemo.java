package com.javaAdvace.concurrent.future;

import java.util.concurrent.CompletableFuture;

/**
 * @author hitopei
 *
 * completableFuture demo
 */
public class CompletableFutureDemo {
    public static void main(String[] args) {
        System.out.println("1.变换结果");
        String result1 = CompletableFuture.supplyAsync(() -> {return "hello";}).thenApplyAsync(v -> v + " world").join();
        System.out.println(result1);

        CompletableFuture.supplyAsync(() -> {return "hello ";}).thenAccept(v -> {
            System.out.println("消费");
            System.out.println("consumer :" + v);
        });
    }
}
