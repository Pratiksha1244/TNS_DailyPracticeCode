package com.daytwo.loopingstatements;

public class ContinueDemo {

	public static void main(String[] args) {

		for(int i=1; i<=100; i++)
		{
			// Odd numbers are skipped
			if(i%2 != 0) 
			{
				continue;
			}
			// Even numbers are printed
			System.out.println(i);
		}
	}

}
