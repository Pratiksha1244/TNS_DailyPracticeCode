package com.daythree.ScannerClass;

public class MethodsDemo {

	  int m=10; // instance variable
	  int n=20;
	  
	
	// non-parameterized method
	public void addition()
	{
		int a=20;    // local variable
		int b= 10;
		int add= a+b;
		System.out.println("Addition of a and b = "+ add);
		System.out.println(m);
	}
	
	// Parameterized method
	public void multiply(int x, int y)
	{
		
		int mult= x*y;
		System.out.println("Multiplication of X and Y = "+ mult);
		
	}
	
	public void StudentData(int id, String name)
	{
		System.out.println("ID : "+id);
		System.out.println("Name : "+ name);
	}
	public static void main(String[] args) {

		MethodsDemo obj1 = new MethodsDemo(); //object creation
		
		//objectname.methodname;
		
		obj1.addition(); // method calling
		obj1.addition();
		obj1.multiply(5,7);
		obj1.multiply(10,2);
		obj1.StudentData(1, "Roshan");
		obj1.StudentData(2, "Malhar");
	}

}
