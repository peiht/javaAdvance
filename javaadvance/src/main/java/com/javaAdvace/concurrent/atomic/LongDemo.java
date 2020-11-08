package com.javaAdvace.concurrent.atomic;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

/**
 * @author hitopei
 *
 * longAdder在多线程条件下表现比AtomicLong好
 *
 * AtomicLong使用CAS自旋锁，如果在竞争激烈的情况下，CAS 操作不断的失败，就会有大量的线程不断的自旋尝试 CAS 会造成 CPU 的极大的消耗。
 *
 *  LongAdder 是针对 Cell 数组的某个 Cell 进行 CAS 操作 ，把线程的名字的 hash 值，作为 Cell 数组的下标，然后对 Cell[i] 的 long 进行 CAS 操作。
 *  简单粗暴的分散了高并发下的竞争压力。
 *
 *  其实我们可以发现，LongAdder 使用了一个 cell 列表去承接并发的 cas，以提升性能，但是 LongAdder 在统计的时候如果有并发更新，可能导致统计的数据有误差。
 *  如果用于自增 id 的生成，就不适合使用 LongAdder 了。这个时候使用 AtomicLong 就是一个明智的选择。
 *  而在 Sentinel 中 LongAdder 承担的只是统计任务，且允许误差。
 */
public class LongDemo {

    public static void main(String[] args) {
        final AtomicLong atomicLong = new AtomicLong();
        final LongAdder longAdder = new LongAdder();

        for (int i = 0; i < 100; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 10_000; j++) {
                        atomicLong.getAndIncrement();
                        longAdder.increment();
                        }
                    }
                }
            ).start();
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("atomicLong : " + atomicLong.get());
        System.out.println("longAdder : " + longAdder.sum());
    }
}
