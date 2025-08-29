package com.daythree.ScannerClass;
import  java.util.Scanner;

public class ScannerDemo {

	public void add(int x, int y)
	{
		int addition=x+y;
		System.out.println(addition);
	}
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter your name: ");
		String name = sc.nextLine();
		System.out.println(name);
		
		System.out.println("Enter value of x and y: ");
		int x = sc.nextInt();
		int y = sc.nextInt();
		
		int add= x+y;
		System.out.println("Addition = "+add);
		
	}

}
