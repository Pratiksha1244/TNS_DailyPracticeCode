//Program to define base class person

package com.tnsif.dayfive.multilevelinheritance;

public class Person {
	
	private String name;
	private long contactNo;
	private long adharNo;
	
	public Person() {
		
	}

	public Person(String name, long contactNo, long adharNo) {
		this.name = name;
		this.contactNo = contactNo;
		this.adharNo = adharNo;
	}

	//getter and setter
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getContactNo() {
		return contactNo;
	}

	public void setContactNo(long contactNo) {
		this.contactNo = contactNo;
	}

	public long getAdharNo() {
		return adharNo;
	}

	public void setAdharNo(long adharNo) {
		this.adharNo = adharNo;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", contactNo=" + contactNo + ", adharNo=" + adharNo + "]";
	}
	
	
	
	
	

}
