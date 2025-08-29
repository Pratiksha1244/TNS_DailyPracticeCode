package com.daytwo.conditionalstatements;

public class ElseIfLadder {

	public static void main(String[] args) {

		 int x = 30;
		 int y = 40;   
		 int z = 50;
		 
		 if(x>y && x>z)
		 {
			 System.out.println("X is greater number");
		 }
		 else if(y>z)
		 {
			 System.out.println("Y is greater number ");
		 }
		 else
		 {
			 System.out.println("Z is greater number");
		 }
	}

}
