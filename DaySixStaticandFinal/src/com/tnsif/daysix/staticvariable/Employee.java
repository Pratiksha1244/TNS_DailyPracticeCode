// Program to demonstrate static variable
package com.tnsif.daysix.staticvariable;

public class Employee {

	// Declare instance variables
	private int empid;
	private String name;
	
	//Declare a static variable companyName with data type String and assign value
	static String companyName="TNS";

	public Employee(int empid, String name) {
	
		this.empid = empid;
		this.name = name;
	}

	@Override
	public String toString() {
		return "Employee [empid=" + empid + ", name=" + name +", Company= "+companyName+"]";
	}
	
	
}
