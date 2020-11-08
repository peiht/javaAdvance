package com.javaAdvace.concurrent.tool;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Administrator
 *
 *  配合线程池使用CountDownLatch
 */
public class CountDownLatchDemo2 {

    private final static int threadCount = 200;
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();

        final CountDownLatch latch = new CountDownLatch(threadCount);

        for (int i = 0; i < threadCount; i++) {
            final int threadNum = i;
            executorService.execute(() -> {
                try {
                    test(threadNum);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    latch.countDown();
                }

            });
        }

        //其他线程执行完成之前一直阻塞在这里
        //执行完成之后再继续执行主线程
        latch.await();
        System.out.println("所有线程都已执行完毕");
        executorService.shutdown();
    }

    private static void test(int threadNum) throws InterruptedException {
        Thread.sleep(100);
        System.out.println(String.format("程序员[%d]执行完成任务", threadNum));
        Thread.sleep(100);
    }
}
