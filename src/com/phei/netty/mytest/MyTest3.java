package com.phei.netty.mytest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 
 * @author jinpeng.zou
 * @version 
 * @since 2018年6月12日
 *
 */

public class MyTest3 {
	
	private static int COUNT = 100000;
	
	public static void main(String[] args) {
		while(true) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					System.out.println("111111111");
				}
			}).start();
		}
	}
	
	
}
