// Program to demonstrate final variable
package com.tnsif.daysix.usingfinal;

public class FinalVariable {
   // final instance variable must be initialized
	final int x =100;
	
	// Declare a static blank final variable
	
	//final static int Y;
	
	// Declare and initialize static blank final variable
	
		final static int Z =30;
		
		
	// final constructor will throw error
//	final public FinalVariable() {
//		super();
//	}

		// final block
//		final{
//			System.out.println("final Block");
//		}
		
	// instance method
	void change()
	{
		//x=20;
	System.out.println(x);	
	}

	@Override
	public String toString() {
		return "FinalVariable [x=" + x + "]";
	}

	public static void main(String[] args)
	{
		FinalVariable f = new FinalVariable();
		System.out.println(f);
		f.change();
	}
	
}
