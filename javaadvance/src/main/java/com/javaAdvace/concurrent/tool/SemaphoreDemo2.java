package com.javaAdvace.concurrent.tool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author Administrator
 *
 *
 */
public class SemaphoreDemo2 {
    private final static int threadCount = 20;
    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(3);

        for (int i = 0; i < threadCount; i++) {
            final int threadNum = i;
            service.execute(() -> {
                try {
                    //acquire , release 相同的时候就相当于串行化执行
                    semaphore.acquire(3);
                    test(threadNum);
                    semaphore.release(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        service.shutdown();
    }

    private static void test(int threadNum) throws InterruptedException{
        System.out.println("id :" + threadNum + ", " + Thread.currentThread().getName());
        Thread.sleep(1000);
    }
}
