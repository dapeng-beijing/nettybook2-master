package com.phei.netty.aio;

import java.util.concurrent.CountDownLatch;

/**
 * 
 * @author jinpeng.zou
 * @created: 2018年5月31日
 * 
 */

public class Player extends Thread{
	
	private static int count = 1;
	private final int id = count++;
	private CountDownLatch latch;
	
	public Player(CountDownLatch latch){
		this.latch = latch;
	}
	
	@Override
	public void run(){
		System.out.println("玩家" + id + "已入场");
		latch.countDown();
	}
	
	public static void main(String[] args) throws InterruptedException {
		CountDownLatch latch = new CountDownLatch(3);
		System.out.println("牌局开始,等待玩家入场");
		new Player(latch).start();
		new Player(latch).start();
		new Player(latch).start();
		//注释掉此行会出现玩家未到齐状态就发牌
		latch.await();
		System.out.println("玩家已到齐,开始发牌.........");
	}

}
