//Program to demonstrate creating objects with constructors
package com.tnsif.daythree.constructor;

import java.util.Scanner;

public class ConstructorDemo {

	public static void main(String[] args) {

		Scanner sc= new Scanner(System.in);

		System.out.println("Enter customer id:");
		int id = sc.nextInt();
		
		System.out.println("Enter Customer Name:");
		String name= sc.next();
		
		System.out.println("Enter Customer City:");
		String city= sc.next();
		
		System.out.println(" ");
		
		Customer c1= new Customer();// default constructor invoked
		
		c1.setCustomerName(name);
		c1.setCustomerId(id);
		c1.setCustomercity(city);
		
		
		Customer c2 = new Customer(name, id ,city); // parametrized constructor invoked
		System.out.println(c2);
		
		
		
	}

}
