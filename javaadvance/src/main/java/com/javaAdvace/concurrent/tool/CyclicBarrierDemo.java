package com.javaAdvace.concurrent.tool;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * CyclicBarrier demo
 *
 * @author Administrator
 */
public class CyclicBarrierDemo {
    public static void main(String[] args) {
        // 最后的执行可以使用一个回调函数
        CyclicBarrier cyclicBarrier = new CyclicBarrier(5, new Runnable() {
            @Override
            public void run() {
                System.out.println("回调线程： " + Thread.currentThread().getName());
                System.out.println("回调线程执行结束");
            }
        });

        for (int i = 0; i < 5; i++) {
            new Thread(new ReadNumCyc(i, cyclicBarrier)).start();
        }

        System.out.println("各个线程执行结束");
        System.out.println("主线程执行结束");
    }
}

class ReadNumCyc implements Runnable{

    private int id;
    private CyclicBarrier cyc;
    public ReadNumCyc(int id, CyclicBarrier cyc) {
        this.id = id;
        this.cyc = cyc;
    }
    @Override
    public void run() {
        synchronized (this) {
            System.out.println("id :" + id + ", " + Thread.currentThread().getName());
            try {
                System.out.println("线程组任务：" + id + "结束，其他任务继续");
                cyc.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }
}
