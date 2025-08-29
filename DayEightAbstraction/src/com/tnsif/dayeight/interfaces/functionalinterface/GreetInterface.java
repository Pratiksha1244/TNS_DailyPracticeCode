package com.tnsif.dayeight.interfaces.functionalinterface;

public interface GreetInterface {

	// variable
	int x = 10; 
	
	// abstract method
	public void show();
	
	void method1();
	
	void method2();
	
	// static method
	static void data()
	{
		//x=67; 
		System.out.println("Static method");
	}
	
	//default method
	default void data1()
	{
		System.out.println("Default method");
	}
	
		public static void main(String[] args)
		{
			//GreetInterface g = new GreetInterface();
		}
	
}
