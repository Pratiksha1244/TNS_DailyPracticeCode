package com.tnsif.dayfive.multilevelinheritance;

public class Employee extends Person{
	
	private String deptName;
	private double baseSalary;
	
	public Employee() {
		super();
	}

	public Employee(String name, long contactNo, long adharNo, String deptName,double baseSalary ) {
		super(name, contactNo, adharNo);
		this.deptName = deptName;
		this.baseSalary = baseSalary;
	}

	// getter and setter
	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public double getBaseSalary() {
		return baseSalary;
	}

	public void setBaseSalary(double baseSalary) {
		this.baseSalary = baseSalary;
	}

	@Override
	public String toString() {
		return "Employee [deptName=" + deptName + ", baseSalary=" + baseSalary + super.toString()
				+ "]";
	}

	
	
	
	
	
	
	
	
	
	

}
