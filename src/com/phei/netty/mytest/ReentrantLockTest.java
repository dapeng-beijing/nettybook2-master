package com.phei.netty.mytest;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 
 * @author jinpeng.zou
 * @version 
 * @since 2018年7月31日
 *
 */

public class ReentrantLockTest extends Thread{
	
	private ReentrantLock lock;
	private int num;
	
	public ReentrantLockTest(ReentrantLock lock,int num) {
		this.lock = lock;
		this.num = num;
	}

	@Override
	public void run() {
		lock.lock();
		try {
			Thread.sleep(1000);
			System.out.println(num);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
	
}
