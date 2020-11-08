package com.javaAdvace.concurrent.tool;

import java.util.concurrent.Semaphore;

/**
 * semaphore 的生产者消费者模式
 * @author Administrator
 */
public class SemaphoreDemo3 {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(new Producer()).start();
            new Thread(new Consumer()).start();
        }
    }

    static Warehouse buffer = new Warehouse();
    static class Producer implements Runnable {
        static int num = 1;
        @Override
        public void run() {
            int n = num++;
            while (true) {
                try {
                    buffer.put(n);
                    System.out.println("> " + n);
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class Consumer implements Runnable{

        @Override
        public void run() {
            while (true) {
                try {
                    System.out.println("<" + buffer.take());
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class Warehouse {
        final Semaphore notFull = new Semaphore(20);
        final Semaphore notEmpty = new Semaphore(0);
        final Semaphore mutex = new Semaphore(1);

        final Object[] items = new Object[10];

        int putptr, takeptr, count;

        public void put(Object obj) throws InterruptedException{
            notFull.acquire();
            mutex.acquire();
            items[putptr] = obj;
            try {
                if (++putptr == items.length) {
                    putptr = 0;
                    ++count;
                }
            } finally {
                mutex.release();
                notEmpty.release();
            }
        }

        public Object take() throws InterruptedException{
            notEmpty.acquire();
            mutex.acquire();
            Object obj = items[takeptr];
            try {
                if (++takeptr == items.length) {
                    takeptr = 0;
                    --count;
                }
                return obj;
            } finally {
                mutex.release();
                notFull.release();
            }
        }
    }
}
