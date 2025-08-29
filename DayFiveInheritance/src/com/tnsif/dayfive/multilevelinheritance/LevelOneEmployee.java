package com.tnsif.dayfive.multilevelinheritance;

public class LevelOneEmployee extends Employee{

	private int noOfShares;
	private String authority;
	
	public LevelOneEmployee() {
		super();
	}

	public LevelOneEmployee(String name, long contactNo, long adharNo, String deptName, double baseSalary, int noOfShares,String authority) {
		super(name, contactNo, adharNo, deptName, baseSalary);
		this.noOfShares = noOfShares;
		this.authority = authority;
	}

	public int getNoOfShares() {
		return noOfShares;
	}

	public void setNoOfShares(int noOfShares) {
		this.noOfShares = noOfShares;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	@Override
	public String toString() {
		return "LevelOneEmployee [noOfShares=" + noOfShares + ", authority=" + authority 
				+ super.toString() + "]";
	}
	
	
	
	
	
}
