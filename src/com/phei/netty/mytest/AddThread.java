package com.phei.netty.mytest;

import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 
 * @author jinpeng.zou
 * @version 
 * @since 2018年7月23日
 *
 */

public class AddThread extends Thread{
	
	private Map<String,AtomicInteger> map;
	private CountDownLatch latch;
	
	public AddThread(Map<String, AtomicInteger> map, CountDownLatch latch) {
		this.map = map;
		this.latch = latch;
	}

	@Override
	public void run() {
		//synchronized(AddThread.class) {
			map.put("apple", new AtomicInteger(map.get("apple").incrementAndGet()));
			latch.countDown();
			
		//}
	}

}
