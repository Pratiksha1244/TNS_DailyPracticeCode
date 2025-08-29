//Program to demonstrate single level inheritance
package com.tnsif.dayfive.singleinheritance;

public class Student extends Citizen{
	
	private int rollNo;
	private String collegeName;
	
	public Student() {
		super();
	}

	public Student(String name, String aadharno, String address, long phno, int rollNo, String collegeName) {
		super(name, aadharno, address, phno); // accessing parent class constructor

		this.rollNo = rollNo;
		this.collegeName= collegeName;
	}
	
	// getter and setter

	public int getRollNo() {
		return rollNo;
	}

	public void setRollNo(int rollNo) {
		this.rollNo = rollNo;
	}

	public String getCollegeName() {
		return collegeName;
	}

	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}

	@Override
	public String toString() {
		return "Student [rollNo=" + rollNo + ", collegeName=" + collegeName + ", Name=" + getName()
				+ ", Aadharno=" + getAadharno() + ", Address=" + getAddress() + ", Phno=" + getPhno()
				+ "]";
	}
	
	
	

	
		
		
	}
	
	

	
	
	
	
	
	


