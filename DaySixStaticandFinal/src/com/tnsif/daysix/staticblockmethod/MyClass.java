package com.tnsif.daysix.staticblockmethod;

public class MyClass {
	
	private int section; //non-static or instance variable
	private static int srNo; // static or class variable

	static{
		System.out.println("Block in java");
	}
	
	static void display()
	{
		System.out.println("Static Method");
		//System.out.println("Section "+ section);
		System.out.println("SrNo "+srNo);
		System.out.println();
	}
	
	void data()
	{
		System.out.println("Non-Static Method");
		System.out.println("Section "+ section);
		System.out.println("SrNo "+srNo);
	}
	
	public static void main(String[] args)
	{
		display();
		//MyClass obj = new MyClass();
		//obj.data();
	}
}
