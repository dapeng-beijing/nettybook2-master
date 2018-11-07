package com.phei.netty.mytest2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 
 * @author jinpeng.zou
 * @version 
 * @since 2018年7月23日
 *
 */

public class RedPacket {

	/**
	 * 
	 * @param sum,红包总金额,以分为单位
	 * @param count,领取红包的人数
	 * @return
	 * @throws Exception 
	 */
	public List<Integer> splitPacket(int sum, int count) throws Exception{
		if (count > sum) {
			throw new Exception("红包金额与人数不匹配,无法分配");
		}
		
		Random random = new Random();
		List<Double> chanceList = new ArrayList<>();
		double sumChance = 0.0;
		for(int i=0;i<count;i++) {
			double nextDouble = random.nextDouble();
			chanceList.add(nextDouble);
			sumChance = sumChance + nextDouble;
		}
		
		List<Integer> result = new ArrayList<>();
		int allotSum = 0;
		
		for(int i=0;i<chanceList.size();i++) {
			if (i != (chanceList.size()-1)) {
				result.add((int)(sum*chanceList.get(i)/sumChance));
				allotSum = allotSum + (int)(sum*chanceList.get(i)/sumChance);
			} else {
				result.add(sum - allotSum);
			}
		}
		
		int sumChange = 0;
		
		for (int i=0;i<result.size();i++) {
			if (result.get(i) < 1) {
				sumChange = 1 - result.get(i) + sumChange;
				result.set(i, 1);
			}
		}
		
		if (sumChange > 0) {
			for (int i=0;i<result.size();i++) {
				if (result.get(i) > 1) {
					if (result.get(i) - 1 > sumChange) {
						result.set(i, result.get(i) - sumChange);
						sumChange = 0;
						break;
					} else {
						sumChange = sumChange - (result.get(i) - 1);
						result.set(i, 1);
					}
				}
			}
		}
		
		return result;
	}
	
	public static void main(String[] args) throws Exception {
		RedPacket packet = new RedPacket();
		List<Integer> list = packet.splitPacket(100, 100);
		System.out.println(list);
		int sum = 0;
		for (Integer integer : list) {
			sum = sum + integer;
		}
		System.out.println(sum);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
