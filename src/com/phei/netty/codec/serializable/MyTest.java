package com.phei.netty.codec.serializable;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import com.caucho.hessian.io.HessianInput;
import com.caucho.hessian.io.HessianOutput;

/**
 * 
 * @author jinpeng.zou
 * @created: 2018年5月31日
 * 
 */

public class MyTest {
	
	
	public static void main(String[] args) throws IOException {
		Person p = new Person();
		byte[] serialize = serialize(p);
		System.out.println(serialize);
	}
	
	
	
	public static byte[] serialize(Object obj) throws IOException{  
	    if(obj==null) throw new NullPointerException();  
	      
	    ByteArrayOutputStream os = new ByteArrayOutputStream();  
	    HessianOutput ho = new HessianOutput(os);  
	    ho.writeObject(obj);  
	    return os.toByteArray();  
	}  
	  
	public static Object deserialize(byte[] by) throws IOException{  
	    if(by==null) throw new NullPointerException();  
	      
	    ByteArrayInputStream is = new ByteArrayInputStream(by);  
	    HessianInput hi = new HessianInput(is);  
	    return hi.readObject();  
	}  
}
