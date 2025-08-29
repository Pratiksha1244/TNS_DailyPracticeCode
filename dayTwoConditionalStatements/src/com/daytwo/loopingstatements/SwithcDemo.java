package com.daytwo.loopingstatements;

public class SwithcDemo {

	public static void main(String[] args) {

		char x ='w';
		
		switch(x)
		{
		case 'l':
		case 'L':
			System.out.println(x+ " is a Letter");
			break;
		case 'd':
		case 'D':
			System.out.println(x+ " is a Digit");
			break;
		case 'w':
		case 'W':
			System.out.println(x +" is a WhiteSpace");
			break;
		case 's':
		case 'S':
			System.out.println(x+ " is a Symbol");
			break;
		default:
			System.out.println(x+" is other than letter, number, whitespace and symbol ");
			break;
				
		 
		}
	}

}
