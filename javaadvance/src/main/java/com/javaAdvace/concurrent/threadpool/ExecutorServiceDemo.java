package com.javaAdvace.concurrent.threadpool;

import java.util.concurrent.*;

/**
 * @author hitopei
 */
public class ExecutorServiceDemo {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newScheduledThreadPool(16);

        try {
            String str = executorService.submit(new Callable<String>() {
                @Override
                public String call() {
                    return "execute finished";
                }
            }).get();
            System.out.println("str : " + str);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }

        int core = Runtime.getRuntime().availableProcessors();
        BlockingQueue<Runnable> queue = new ArrayBlockingQueue(20);
        queue.offer(new Thread());
        ThreadPoolExecutor executor = new ThreadPoolExecutor(core, core, 100, TimeUnit.MILLISECONDS, queue);
        executor.execute(new Runnable() {
            @Override
            public void run() {

            }
        });
    }
}
