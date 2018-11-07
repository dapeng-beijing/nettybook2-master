package com.phei.netty.mytest;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyService {

	
    
    public static void main(String[] args) throws InterruptedException {
    	final ReentrantLock lock = new ReentrantLock();
    	
    	for(int i=1;i<=5;i++) {
    		ReentrantLockTest test = new ReentrantLockTest(lock, i);
    		test.start();
    	}
    	Thread.sleep(11000);
    	
	}

}