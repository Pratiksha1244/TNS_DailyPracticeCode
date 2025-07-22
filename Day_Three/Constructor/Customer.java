package com.tnsif.daythree.constructor;

public class Customer {
	
	private String customerName;
	private int customerId;
	private String customercity;
	
	//Default Constructor
	public Customer()
	{
		System.out.println("Default Constructor");
	}
	
	//Parameterized Constructor
	 public Customer(String customerName, int customerId, String customercity)
	 {
		 this(); // calling default constructor
		 System.out.println("Parameterized constructor");
		 this.customerName=customerName;
		 this.customerId=customerId;
		 this.customercity = customercity;
	 }

		//Getter and Setter
	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getCustomercity() {
		return customercity;
	}

	public void setCustomercity(String customercity) {
		this.customercity = customercity;
	}

	@Override
	public String toString() {
		return "Customer [customerName=" + customerName + ", customerId=" + customerId + ", customercity="
				+ customercity + "]";
	}
	 

	
	 
	 
	 
	 
	
}
