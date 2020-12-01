package com.javaAdvace.concurrent.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockDemo {

    private final Map<String, Object> map = new HashMap<>();
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public Object readWrite(String key) {
        Object value = null;
        System.out.println("开启读锁去缓存中取数据");
        readWriteLock.readLock().lock();
        try {
            value = map.get(key);
            if (value == null) {
                System.out.println("数据不存在，释放读锁，开启写锁");
                readWriteLock.readLock().unlock();
                readWriteLock.writeLock().lock();
                try {
                    if (value == null) {
                        value = "aaa";
                    }
                } finally {
                    System.out.println("释放写锁");
                    readWriteLock.writeLock().unlock();
                }
                System.out.println("开启读锁");
                readWriteLock.readLock().lock();
            }
        } finally {
            System.out.println("释放读锁");
            readWriteLock.readLock().unlock();
        }
        return value;
    }

    public static void main(String[] args) {
        ReadWriteLockDemo demo = new ReadWriteLockDemo();
        System.out.println(demo.readWrite("111"));
    }
}
