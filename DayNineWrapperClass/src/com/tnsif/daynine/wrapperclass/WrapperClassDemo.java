package com.tnsif.daynine.wrapperclass;

public class WrapperClassDemo {

	public static void main(String[] args) {

		
		
		
		Byte b = 1;
		
		Integer i1 = 20;
		System.out.println(i1);
		
		// AutoBoxing 
		int a = 100;
		Integer i2 = a;
		System.out.println(i2);
		
		//Unboxing 
		Integer i = new Integer(10);
		System.out.println(i);
		int d = i.intValue();
		System.out.println(d);
		
		// without using intValue()
		 int c = i;
		 System.out.println(c);
		
	}

}
