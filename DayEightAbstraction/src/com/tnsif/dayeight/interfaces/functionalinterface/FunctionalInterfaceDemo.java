package com.tnsif.dayeight.interfaces.functionalinterface;

public class FunctionalInterfaceDemo {

	public static void main(String[] args) {

		GreetClass g = new GreetClass();
		System.out.println(g.greet());
		
		// using lambda expressions
		
		Greetings g1 = () -> {
			return "Good Afternoon";
		};
		
		System.out.println(g1.greet());
		
		
	}

}
