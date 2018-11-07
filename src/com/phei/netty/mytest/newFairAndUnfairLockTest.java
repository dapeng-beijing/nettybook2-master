package com.phei.netty.mytest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 改进后的代码，利用CyclicBarrier当所有线程执行完毕时，统计执行时间。
 * Created by yulinfeng on 5/24/17.
 */
public class newFairAndUnfairLockTest {
    private static Lock lock = new ReentrantLockMine(false);    //非公平锁
    //private static Lock lock = new ReentrantLockMine(true);   //公平锁

    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {
        String lockType = "非公平锁"; //String lockType = "公平锁"
        long start = System.currentTimeMillis();
        CyclicBarrier cyclicBarrier = new CyclicBarrier(10, new Time(lockType, start));     //10个线程执行完毕时，执行Time线程统计执行时间

        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new Job(lock, cyclicBarrier)){
                public String toString() {
                    return getName();
                }
            };
            thread.setName("" + i);
            thread.start();
        }


    }

    private static class Job implements Runnable{
        private Lock lock;
        private CyclicBarrier cyclicBarrier;
        public Job(Lock lock, CyclicBarrier cyclicBarrier) {
            this.lock = lock;
            this.cyclicBarrier = cyclicBarrier;
        }

        public void run() {
            for (int i = 0; i < 100000; i++) {
                lock.lock();
                try {
                    System.out.println(i+"获取锁的当前线程[" + Thread.currentThread().getName() + "], 同步队列中的线程" + ((ReentrantLockMine)lock).getQueuedThreads() + "");
                } finally {
                    lock.unlock();
                }
            }
            try {
                cyclicBarrier.await();  //计数器+1，直到10个线程都到达
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }

    private static class ReentrantLockMine extends ReentrantLock {  //重新实现ReentrantLock类是为了重写getQueuedThreads方法，便于我们试验的观察
        public ReentrantLockMine(boolean fair) {
            super(fair);
        }

        @Override
        protected Collection<Thread> getQueuedThreads() {   //获取同步队列中的线程
            List<Thread> arrayList = new ArrayList<Thread>(super.getQueuedThreads());
            Collections.reverse(arrayList);
            return arrayList;
        }
    }


    private static class Time implements Runnable {     //用于统计时间
        private long start ;
        private String lockType;

        public Time(String lockType, long start) {
            this.start = start;
            this.lockType = lockType;
        }

        public void run() {
            System.out.println(lockType + "耗时:" + String.valueOf(System.currentTimeMillis() - start));
        }
    }
}