package com.alphabethub.juc;

import java.util.HashMap;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 面试题：设计一个支持高并发的缓存系统
 */
public class CacheDemo {
    //定义缓存的容器
    private HashMap<String, Object> cache = new HashMap<>();
    //要支持高并发的读写并保证线程安全就要使用读写锁
    private ReadWriteLock rwl = new ReentrantReadWriteLock();

    public static void main(String[] args) {

    }

    public Object getData(String key) {
        rwl.readLock().lock();
        Object value = null;
        try {
            value = cache.get(key);
            if (value == null) {
                rwl.readLock().unlock();
                rwl.writeLock().lock();
                try {
                    if (value == null) {//双重判空：因为如果3个线程同时排队，只能有一个获取到写锁，写完以后另外阻塞的就会进入
                        value = "select * from t";//从数据库查询
                    }
                } finally {
                    rwl.writeLock().unlock();
                }
                rwl.readLock().lock();
            }
        } finally {
            rwl.readLock().unlock();
        }
        return value;
    }
}
