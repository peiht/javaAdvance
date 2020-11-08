package com.javaAdvace.concurrent.tool;

import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch demo
 *
 * @author Administrator
 */
public class CountDownLatchDemo {
    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(5);
        for (int i = 0; i < 5; i++) {
            new Thread(new ReadNum(i, latch)).start();
        }

        try {
            //主线程等待子线程执行完毕
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("各个线程执行结束");
        System.out.println("主线程执行结束");
    }


}

class ReadNum implements Runnable {

    private int id;
    private CountDownLatch latch;

    public ReadNum(int id, CountDownLatch latch) {
        this.id = id;
        this.latch = latch;
    }

    @Override
    public void run() {
        synchronized (this) {
            System.out.println("id : " + id + ", " + Thread.currentThread().getName());
            System.out.println("线程组任务" + id + "执行结束，其他任务继续执行");
            //执行完成 countDown 减一操作
            latch.countDown();
        }
    }
}
