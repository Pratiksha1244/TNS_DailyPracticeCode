package com.tnsif.daysix.staticvariable;

public class EmployeeDemo {

	public static void main(String[] args) {

		Employee e = new Employee(123, "Shubhangi");
		System.out.println(e);
		
		Employee e1 = new Employee(101, "Vikas");
		System.out.println(e1);
		
		System.out.println(Employee.companyName);
				
	}

}
