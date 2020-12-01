package com.javaAdvace.concurrent.lock;

import java.util.concurrent.locks.LockSupport;

/**
 * @author hitopei
 * <p>
 * LockSupport demo
 */
public class LockSupportDemo {
    public static Object o = new Object();
    static ChangeObjectThread t1 = new ChangeObjectThread("t1");
    static ChangeObjectThread t2 = new ChangeObjectThread("t2");

    public static class ChangeObjectThread extends Thread {

        public ChangeObjectThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            synchronized (o) {
                System.out.println("in " + getName());
                LockSupport.park();
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println(Thread.currentThread().getName() + "线程被中断了");
                }
                System.out.println(Thread.currentThread().getName() + "继续执行");
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        t1.start();
        Thread.sleep(1000);
        t2.start();
        Thread.sleep(3000);
        t1.interrupt();
        LockSupport.unpark(t2);
        t1.join();
        t2.join();
    }
}
