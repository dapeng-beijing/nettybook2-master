package com.phei.netty.mytest;

/**
 * 
 * @author jinpeng.zou
 * @created: 2018年8月2日
 * 
 */

public class Apple implements Comparable<Apple>{

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Apple(String name) {
		this.name = name;
	}

	@Override
	public int compareTo(Apple o) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
}
