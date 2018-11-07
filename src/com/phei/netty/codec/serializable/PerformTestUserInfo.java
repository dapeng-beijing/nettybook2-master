package com.phei.netty.codec.serializable;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.ByteBuffer;

/**
 * @author Administrator
 * @date 2014年2月23日
 * @version 1.0
 */
public class PerformTestUserInfo {

    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
		UserInfo info = new UserInfo();
		info.buildUserID(100).buildUserName("Welcome to Netty");
		int loop = 1000000;
		ByteArrayOutputStream bos = null;
		ObjectOutputStream os = null;
		long startTime = System.currentTimeMillis();
		for (int i = 0; i < loop; i++) {
		    bos = new ByteArrayOutputStream();
		    os = new ObjectOutputStream(bos);
		    os.writeObject(info);
		    os.flush();
		    os.close();
		    byte[] b = bos.toByteArray();
		    bos.close();
		}
		long endTime = System.currentTimeMillis();
		System.out.println("The jdk serializable cost time is  : "
			+ (endTime - startTime) + " ms");
	
		System.out.println("-------------------------------------");
	
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		startTime = System.currentTimeMillis();
		for (int i = 0; i < loop; i++) {
		    byte[] b = info.codeC(buffer);
		}
		endTime = System.currentTimeMillis();
		System.out.println("The byte array serializable cost time is : "
			+ (endTime - startTime) + " ms");

    }

}
