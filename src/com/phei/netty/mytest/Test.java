package com.phei.netty.mytest;

/**
 * 
 * @author jinpeng.zou
 * @created: 2018年8月31日
 * 
 */

public class Test {
	{
		System.out.println("Block A");
	}
	static {
		System.out.println("Block B");
	}
//	static Test t1 = new Test();
	
	
	
	
	public static void main(String[] args) {
		Test t2 = new Test();
	}
	
}
