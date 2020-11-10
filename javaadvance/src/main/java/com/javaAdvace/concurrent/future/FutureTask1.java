package com.javaAdvace.concurrent.future;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Future Task demo
 *
 * @author hitopei
 */
public class FutureTask1 {
    public static void main(String[] args) {
        FutureTask<Integer> task = new FutureTask<>(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                Thread.sleep(1000);
                return new Random().nextInt();
            }
        });

        new Thread(task).start();

        System.out.println("start");
        try {
            System.out.println("结果：" + task.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
